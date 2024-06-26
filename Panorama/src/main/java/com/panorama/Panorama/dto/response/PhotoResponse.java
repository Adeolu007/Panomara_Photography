package com.panorama.Panorama.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponse {
    private String title;
    private String url;
    private Long photoSessionId;
}
