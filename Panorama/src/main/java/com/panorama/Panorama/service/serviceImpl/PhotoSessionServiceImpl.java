package com.panorama.Panorama.service.serviceImpl;

import com.panorama.Panorama.dto.request.PhotoSessionRequest;
import com.panorama.Panorama.entity.Admin;
import com.panorama.Panorama.entity.PhotoSession;
import com.panorama.Panorama.repository.AdminRepository;
import com.panorama.Panorama.repository.PhotoSessionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PhotoSessionServiceImpl {
private final PhotoSessionRepository photoSessionRepository;
private final AdminRepository adminRepository;
    public String registerPhotoSession (PhotoSessionRequest photoSessionRequest){
        Admin admin = adminRepository.findById(photoSessionRequest.getAdminId()).orElseThrow();
        String adminRoleName = admin.getRoles().stream()
                .findFirst()
                .map(role -> role.getRolename())
                .orElse(null);
        if(adminRepository.existsById(photoSessionRequest.getAdminId())){
            return "PhotoSession already exists";
        }

        else if(!"ROLE_ADMIN".equals(adminRoleName)){
            return "You do not have permission";
        }
        PhotoSession.builder().title(photoSessionRequest.getTitle())
                .date(photoSessionRequest.getDate())
                .description(photoSessionRequest.getDescription())
                .title(photoSessionRequest.getTitle())
                .admin(admin).build();

        return  "photo session created";
    }
}
