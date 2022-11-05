package com.henry.bookrecommendationsystem.service;


import com.henry.bookrecommendationsystem.dto.base.request.RefreshTokenRequest;
import com.henry.bookrecommendationsystem.entity.RefreshToken;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
public interface RefreshTokenService {
    RefreshToken findRefreshTokenByRefreshToken(String refreshToken);

    RefreshToken createRefreshToken(String email);

    RefreshToken refreshToken(RefreshTokenRequest refreshTokenRequest);

    Boolean deleteRefreshToken(String email);
}
