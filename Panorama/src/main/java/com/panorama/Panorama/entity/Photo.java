package com.panorama.Panorama.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
@Builder
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;

    @ManyToOne
    @JoinColumn(name = "photo_session_id")
    private PhotoSession photoSession;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
