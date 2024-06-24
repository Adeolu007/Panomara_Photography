package com.panorama.Panorama.dto.request;

import com.panorama.Panorama.entity.Admin;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoSessionRequest {
    private String title;
    private LocalDate date;
    private String description;
    private Long adminId;
}
