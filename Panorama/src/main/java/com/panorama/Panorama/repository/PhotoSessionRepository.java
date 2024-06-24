package com.panorama.Panorama.repository;

import com.panorama.Panorama.entity.PhotoSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoSessionRepository extends JpaRepository<PhotoSession, Long> {

    @Override
    boolean existsById(Long id);
}
