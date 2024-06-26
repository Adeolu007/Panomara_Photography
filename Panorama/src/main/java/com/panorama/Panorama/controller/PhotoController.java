package com.panorama.Panorama.controller;

import com.panorama.Panorama.dto.request.PhotoRequest;
import com.panorama.Panorama.dto.response.PhotoResponse;
import com.panorama.Panorama.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/photo")
@AllArgsConstructor
public class PhotoController {
    private PhotoService photoService;

    @PostMapping("/add")
    public PhotoResponse addPhoto(@RequestBody PhotoRequest photoRequest) {
        return photoService.addPhoto(photoRequest);
    }

    @DeleteMapping("/delete/{photoId}")
    public void deletePhoto(@PathVariable Long photoId) {
        photoService.deletePhoto(photoId);
    }

    @GetMapping("/admin/{adminId}")
    public List<PhotoResponse> getAllPhotosByAdminId(@PathVariable Long adminId) {
        return photoService.getAllPhotosByAdminId(adminId);
    }

    @GetMapping("/session/{photoSessionId}")
    public List<PhotoResponse> getAllPhotosByPhotoSessionId(@PathVariable Long photoSessionId) {
        return photoService.getAllPhotosByPhotoSessionId(photoSessionId);
    }
}
