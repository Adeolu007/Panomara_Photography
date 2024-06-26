package com.panorama.Panorama.service.serviceImpl;

import com.panorama.Panorama.dto.request.PhotoSessionRequest;
import com.panorama.Panorama.entity.Admin;
import com.panorama.Panorama.entity.Photo;
import com.panorama.Panorama.entity.PhotoSession;
import com.panorama.Panorama.repository.AdminRepository;
import com.panorama.Panorama.repository.PhotoSessionRepository;
import com.panorama.Panorama.service.PhotoSessionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PhotoSessionServiceImpl implements PhotoSessionService {
private final PhotoSessionRepository photoSessionRepository;
private final AdminRepository adminRepository;

    @Transactional
    public String registerPhotoSession (PhotoSessionRequest photoSessionRequest, Long id){
     //   Admin admin = adminRepository.findById(photoSessionRequest.getAdminId()).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
//        Long adminId = photoSessionRequest.getAdminId();
        Long adminId =  id;
        log.info("Admin ID received: {}", adminId);
        if (adminId == null) {
            return "Admin ID cannot be null";
        }

        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isEmpty()) {
            return "Admin not found";
        }


        Admin admin = adminOptional.get();
     //    Admin admin = adminRepository.findById(photoSessionRequest.getAdminId()).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        String adminRoleName = admin.getRoles().stream()
                .findFirst()
                .map(role -> role.getRolename())
                .orElse(null);
        log.info("Admin details: id={}, firstName={}, lastName={}, userName={}, email={}",
                admin.getId(), admin.getFirstName(), admin.getLastName(), admin.getUserName(), admin.getEmail());

//        if(adminRepository.existsById(photoSessionRequest.getAdminId())){
//            return "PhotoSession already exists";
//        }

         if(!"ROLE_ADMIN".equals(adminRoleName)){
            return "You do not have permission";
        }
//        PhotoSession photoSession = PhotoSession.builder().title(photoSessionRequest.getTitle())
//                .date(photoSessionRequest.getDate())
//                .description(photoSessionRequest.getDescription())
//                .title(photoSessionRequest.getTitle())
//                .admin(admin).build();
        PhotoSession photoSession = new PhotoSession();
         photoSession.setTitle(photoSessionRequest.getTitle());
         photoSession.setDate(photoSessionRequest.getDate());
         photoSession.setDescription(photoSessionRequest.getDescription());
         photoSession.setAdmin(admin);
        log.info("New Admin details: title={}, date={}, description={}",
                photoSession.getTitle(), photoSession.getDate(), photoSession.getDescription());

        photoSessionRepository.save(photoSession);
        return  "photo session created";
    }

    @Override
    public String addPhotos(String[] photoUrls, Long id) {
        if (!photoSessionRepository.existsById(id)){
            return "Photo Session does not exist, please input a valid Photo Session id";
        }

        PhotoSession photoSession = photoSessionRepository.findById(id) .orElseThrow(() -> new NoSuchElementException("PhotoSession not found with id: " + id));
        // Convert String[] photoUrls to Set<Photo> (assuming Photo is an entity with a URL field)
        Set<Photo> photos = convertToPhotos(photoUrls);

        // Set photos to the PhotoSession entity
        photoSession.getPhotos().addAll(photos);

        // Save the updated PhotoSession entity
        photoSessionRepository.save(photoSession);

        return "Photos have been added to photo session";
    }

    @Override
    public String deletePhotoSession(Long id) {
        return null;
    }

    private Set<Photo> convertToPhotos(String[] photoUrls) {
        return Arrays.stream(photoUrls)
                .map(url -> {
                    Photo photo = new Photo();
                    photo.setUrl(url);
                    return photo;
                })
                .collect(Collectors.toSet());
    }
}
