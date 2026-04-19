package com.example.market_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(name ="profile_image")
    private String profileImage;

    @Column(name = "manner_score")
    private Double mannerScore = 36.5;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_ad")
    private LocalDateTime updatedAd;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAd = LocalDateTime.now();
    }
}