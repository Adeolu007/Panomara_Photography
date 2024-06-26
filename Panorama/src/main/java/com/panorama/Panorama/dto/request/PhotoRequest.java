package com.panorama.Panorama.dto.request;

import com.panorama.Panorama.entity.Admin;
import com.panorama.Panorama.entity.PhotoSession;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoRequest {
    private String title;
    private String url;
    private Long photoSessionId;
    private Long adminId;
}
