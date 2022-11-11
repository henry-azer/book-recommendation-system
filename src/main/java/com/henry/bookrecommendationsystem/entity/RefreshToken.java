package com.henry.bookrecommendationsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "refresh_token", schema = "public")
public class RefreshToken {

    @Id
    @SequenceGenerator(name = "refresh_token_id_sequence", sequenceName = "refresh_token_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refresh_token_id_sequence")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "email")
    private String email;

    @Column(name = "refresh_count")
    private Long refreshCount;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "marked_as_deleted")
    private Boolean markedAsDeleted = false;
}
