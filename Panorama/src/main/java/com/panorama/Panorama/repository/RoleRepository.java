package com.panorama.Panorama.repository;

import com.panorama.Panorama.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRolename(String rolename);

    boolean existsByRolename(String rolename);

}