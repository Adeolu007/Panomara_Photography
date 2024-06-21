package com.panorama.Panorama.service;

import com.panorama.Panorama.dto.request.AdminRegisterRequest;
import com.panorama.Panorama.dto.request.LoginRequest;
import com.panorama.Panorama.dto.response.AuthResponse;

public interface AdminService {
    String register (AdminRegisterRequest adminRegisterRequest);
    AuthResponse login (LoginRequest loginRequest);
}
