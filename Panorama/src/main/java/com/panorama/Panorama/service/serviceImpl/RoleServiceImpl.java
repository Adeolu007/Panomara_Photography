package com.panorama.Panorama.service.serviceImpl;

import com.panorama.Panorama.dto.request.RoleRequest;
import com.panorama.Panorama.dto.response.RoleResponse;
import com.panorama.Panorama.entity.RoleEntity;
import com.panorama.Panorama.repository.RoleRepository;
import com.panorama.Panorama.service.RoleService;
import com.panorama.Panorama.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RoleResponse addRole(RoleRequest request) {
        boolean isExists = roleRepository.existsByRolename(request.getRoleName());
        if(isExists){
            throw new CustomException(ResponseUtils.ROLE_EXISTS_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        RoleEntity roles = RoleEntity.builder()
                .rolename(request.getRoleName())
                .build();
        roleRepository.save(roles);
        return RoleResponse.builder()
                .id(roles.getId())
                .roleName(roles.getRolename())
                .build();
    }

//    @Override
//    public RoleResponse addRole(RoleRequest request) {
//        return null;
//    }

    @Override
    public void deleteRole(Long id) {
        RoleEntity role =roleRepository.findById(id)
                .orElseThrow(()-> new CustomException(ResponseUtils.ROLE_NOT_FOUND_MESSAGE,HttpStatus.BAD_REQUEST));
        roleRepository.delete(role);
    }

}
