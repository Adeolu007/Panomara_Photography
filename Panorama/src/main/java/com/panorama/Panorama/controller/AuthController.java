package com.panorama.Panorama.controller;

import com.panorama.Panorama.dto.request.AdminRegisterRequest;
import com.panorama.Panorama.dto.request.LoginRequest;
import com.panorama.Panorama.dto.response.AuthResponse;
import com.panorama.Panorama.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AdminService adminService;
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody AdminRegisterRequest adminRegisterRequest) {
        return adminService.register(adminRegisterRequest);
    }

    @PostMapping("/login")
    public AuthResponse loginAdmin(@RequestBody LoginRequest loginRequest) {
        return adminService.login(loginRequest);
    }
}
