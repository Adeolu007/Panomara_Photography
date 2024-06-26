package com.panorama.Panorama.service;

import com.panorama.Panorama.dto.request.PhotoRequest;
import com.panorama.Panorama.dto.response.PhotoResponse;

import java.util.List;

public interface PhotoService {
    PhotoResponse addPhoto(PhotoRequest photoRequest);
    void deletePhoto(Long photoId);
    List<PhotoResponse> getAllPhotosByAdminId(Long adminId);
    List<PhotoResponse> getAllPhotosByPhotoSessionId(Long photoSessionId);
}
