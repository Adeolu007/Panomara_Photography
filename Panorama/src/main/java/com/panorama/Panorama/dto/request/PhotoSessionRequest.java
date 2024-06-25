package com.panorama.Panorama.dto.request;

import java.time.LocalDate;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoSessionRequest {
    private String title;
    private LocalDate date;
    private String description;
//    private Long adminId;
}
