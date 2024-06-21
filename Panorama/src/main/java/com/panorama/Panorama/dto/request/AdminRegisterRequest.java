package com.panorama.Panorama.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AdminRegisterRequest {
    private String firstName;
    private String lastName;
    private String otherName;
    private String userName;
    private String email;
    private String password;
}