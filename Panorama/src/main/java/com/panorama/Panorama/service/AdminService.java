package com.panorama.Panorama.service;

import com.panorama.Panorama.dto.request.AdminRegisterRequest;
import com.panorama.Panorama.dto.request.LoginRequest;
import com.panorama.Panorama.dto.response.AuthResponse;
import com.panorama.Panorama.entity.Admin;

public interface AdminService {
    String register (AdminRegisterRequest adminRegisterRequest);
    AuthResponse login (LoginRequest loginRequest);

//    Admin getAdmin (Long id);
}
