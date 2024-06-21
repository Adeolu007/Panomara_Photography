package com.panorama.Panorama.controller;

import com.panorama.Panorama.dto.request.RoleRequest;
import com.panorama.Panorama.dto.response.ApiResponse;
import com.panorama.Panorama.dto.response.RoleResponse;
import com.panorama.Panorama.service.RoleService;
import com.panorama.Panorama.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> addRole(@RequestBody RoleRequest request){
        RoleResponse role = roleService.addRole(request);
        ApiResponse<RoleResponse> ar = new ApiResponse<>(HttpStatus.CREATED);
        ar.setMessage(ResponseUtils.SUCCESS_MESSAGE);
        ar.setData(role);
        return new ResponseEntity<>(ar,ar.getStatus());
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteRole(@RequestParam(name = "id") Long id) {
        roleService.deleteRole(id);
        ApiResponse<Void> ar = new ApiResponse<>(HttpStatus.CREATED);
        ar.setMessage(ResponseUtils.USER_DELETED_MESSAGE);
        ar.setData(null);
        return new ResponseEntity<>(ar,ar.getStatus());
    }

}