package com.henry.bookrecommendationsystem.repository;

import com.henry.bookrecommendationsystem.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmailAndMarkedAsDeletedFalse(String email);

    Optional<RefreshToken> findByTokenAndMarkedAsDeletedFalse(String refreshToken);

    @Modifying
    @Query("UPDATE RefreshToken SET markedAsDeleted = true WHERE email = :email")
    Integer deleteRefreshTokenByEmail(String email);
}
