package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.entity.RefreshToken;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
public interface RefreshTokenDao {
    Optional<RefreshToken> findRefreshTokenByEmail(String email);

    Optional<RefreshToken> findRefreshTokenByRefreshToken(String refreshToken);

    RefreshToken createRefreshToken(RefreshToken refreshToken);

    RefreshToken updateRefreshToken(RefreshToken refreshToken);

    Boolean deleteRefreshTokenByEmail(String email);
}
