package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.entity.RefreshToken;
import com.henry.bookrecommendationsystem.repository.RefreshTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Component
public class RefreshTokenDaoImpl implements RefreshTokenDao {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenDaoImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public Optional<RefreshToken> findRefreshTokenByEmail(String email) {
        return refreshTokenRepository.findByEmailAndMarkedAsDeletedFalse(email);
    }

    @Override
    public Optional<RefreshToken> findRefreshTokenByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByTokenAndMarkedAsDeletedFalse(refreshToken);
    }

    @Override
    public RefreshToken createRefreshToken(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken updateRefreshToken(RefreshToken refreshToken) {
        return refreshTokenRepository.saveAndFlush(refreshToken);
    }

    @Override
    public Boolean deleteRefreshTokenByEmail(String email) {
        return refreshTokenRepository.deleteRefreshTokenByEmail(email) == 1;
    }
}
