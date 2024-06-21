package com.panorama.Panorama.service;

import com.panorama.Panorama.dto.request.RoleRequest;
import com.panorama.Panorama.dto.response.RoleResponse;

public interface RoleService {
    RoleResponse addRole(RoleRequest request);
    void deleteRole(Long id);

}