package com.panorama.Panorama.service.serviceImpl;

import com.panorama.Panorama.dto.request.AdminRegisterRequest;
import com.panorama.Panorama.dto.request.LoginRequest;
import com.panorama.Panorama.dto.response.AuthResponse;
import com.panorama.Panorama.entity.Admin;
import com.panorama.Panorama.entity.RoleEntity;
import com.panorama.Panorama.entity.Status;
import com.panorama.Panorama.repository.AdminRepository;
import com.panorama.Panorama.repository.RoleRepository;
import com.panorama.Panorama.security.config.JwtTokenProvider;
import com.panorama.Panorama.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
@Slf4j
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    //private final EmailService emailService;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(AdminRegisterRequest adminRegisterRequest) {
        log.info(String.format("The name is '%s'", adminRegisterRequest.getUserName()));
        log.info("Password received: " + adminRegisterRequest.getPassword());
        if (adminRepository.existsByEmail(adminRegisterRequest.getEmail())) {
            return "Username or Email is already taken";
        }

        RoleEntity role = roleRepository.findByRolename("ROLE_ADMIN").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        log.info(String.valueOf(adminRegisterRequest.getPassword()));
        log.info(String.valueOf(adminRegisterRequest.getEmail()));
        Admin admin = Admin.builder()
                .firstName(adminRegisterRequest.getFirstName())
                .lastName(adminRegisterRequest.getLastName())
                .userName(adminRegisterRequest.getUserName())
                .email(adminRegisterRequest.getEmail())
                .password(passwordEncoder.encode(adminRegisterRequest.getPassword()))
                .status(Status.ACTIVE)
                .roles(Collections.singleton(role))
                .build();

        adminRepository.save(admin);

        return "Admin registered successfully";

    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        log.info("Welcome to Login Endpoint");
        log.info("Password: " +loginRequest.getPassword()+" Username: "+loginRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return AuthResponse.builder()
                .token(jwtTokenProvider.generateToken(authentication))
                .build();
    }
}
