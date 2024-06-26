package com.panorama.Panorama.repository;

import com.panorama.Panorama.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByAdminId(Long adminId);
    List<Photo> findAllByPhotoSessionId(Long photoSessionId);
}
