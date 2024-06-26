package com.panorama.Panorama.controller;

import com.panorama.Panorama.dto.request.PhotoSessionRequest;
import com.panorama.Panorama.service.PhotoSessionService;
import com.panorama.Panorama.service.serviceImpl.PhotoSessionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photo-session")
@AllArgsConstructor
public class PhotoSessionController {
    private final PhotoSessionServiceImpl photoSessionService;

    @PostMapping("/register-photo-session/{id}")
    public String registerPhotoSession (@RequestBody PhotoSessionRequest photoSessionRequest, @PathVariable Long id){
        return photoSessionService.registerPhotoSession(photoSessionRequest, id);
    }

    @PostMapping("/{id}/add-photos")
    public String a(@RequestBody String[] photoUrls, @PathVariable Long id) {
        return photoSessionService.addPhotos(photoUrls, id);
    }
}
