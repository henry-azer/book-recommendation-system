package com.henry.bookrecommendationsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "email")
    private String email;

    @Column(name = "refresh_count")
    private Long refreshCount;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "marked_as_deleted")
    private Boolean markedAsDeleted = false;
}
