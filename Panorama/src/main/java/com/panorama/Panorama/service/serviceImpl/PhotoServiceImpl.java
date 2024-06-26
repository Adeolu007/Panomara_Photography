package com.panorama.Panorama.service.serviceImpl;

import com.panorama.Panorama.dto.request.PhotoRequest;
import com.panorama.Panorama.dto.response.PhotoResponse;
import com.panorama.Panorama.entity.Admin;
import com.panorama.Panorama.entity.Photo;
import com.panorama.Panorama.entity.PhotoSession;
import com.panorama.Panorama.repository.AdminRepository;
import com.panorama.Panorama.repository.PhotoRepository;
import com.panorama.Panorama.repository.PhotoSessionRepository;
import com.panorama.Panorama.service.PhotoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final AdminRepository adminRepository;
    private final PhotoSessionRepository photoSessionRepository;

    @Override
    public PhotoResponse addPhoto(PhotoRequest photoRequest) {
        Photo photo = new Photo();
        photo.setTitle(photoRequest.getTitle());
        photo.setUrl(photoRequest.getUrl());

        // Fetch PhotoSession by photoSessionId
        PhotoSession photoSession = photoSessionRepository.findById(photoRequest.getPhotoSessionId())
                .orElseThrow(() -> new IllegalArgumentException("PhotoSession not found"));
        photo.setPhotoSession(photoSession);

        // Fetch Admin by adminId
        Admin admin = adminRepository.findById(photoRequest.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        photo.setAdmin(admin);

        // Save the photo
        Photo savedPhoto = photoRepository.save(photo);

        // Prepare response
        return PhotoResponse.builder()
                .title(savedPhoto.getTitle())
                .url(savedPhoto.getUrl())
                .photoSessionId(savedPhoto.getPhotoSession().getId())
                .build();
    }


    @Override
    public void deletePhoto(Long photoId) {
        photoRepository.deleteById(photoId);
    }

    @Override
    public List<PhotoResponse> getAllPhotosByAdminId(Long adminId) {
        List<Photo> photos = photoRepository.findAllByAdminId(adminId);
        return mapPhotosToPhotoResponses(photos);
    }

    @Override
    public List<PhotoResponse> getAllPhotosByPhotoSessionId(Long photoSessionId) {
        List<Photo> photos = photoRepository.findAllByPhotoSessionId(photoSessionId);
        return mapPhotosToPhotoResponses(photos);
    }

    private List<PhotoResponse> mapPhotosToPhotoResponses(List<Photo> photos) {
        return photos.stream()
                .map(photo -> PhotoResponse.builder()
                        .title(photo.getTitle())
                        .url(photo.getUrl())
                        .photoSessionId(photo.getPhotoSession().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
