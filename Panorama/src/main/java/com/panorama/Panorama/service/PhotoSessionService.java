package com.panorama.Panorama.service;

import com.panorama.Panorama.dto.request.PhotoSessionRequest;

public interface PhotoSessionService {
    String registerPhotoSession (PhotoSessionRequest photoSessionRequest,  Long id);
    String addPhotos(String[] photos, Long id);
    String deletePhotoSession(Long id);

}
