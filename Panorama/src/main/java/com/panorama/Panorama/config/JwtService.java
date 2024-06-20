package com.panorama.Panorama.config;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
