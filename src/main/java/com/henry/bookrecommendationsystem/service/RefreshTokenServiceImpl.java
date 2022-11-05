package com.henry.bookrecommendationsystem.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.henry.bookrecommendationsystem.dao.RefreshTokenDao;
import com.henry.bookrecommendationsystem.dto.base.request.RefreshTokenRequest;
import com.henry.bookrecommendationsystem.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenDao refreshTokenDao;

    @Value("${library.security.jwt.refreshToken.expirationMs}")
    private String JWT_REFRESH_TOKEN_EXPIRATION_MS;

    @Override
    public RefreshToken findRefreshTokenByRefreshToken(String refreshToken) {
        log.info("RefreshTokenService: findRefreshTokenByRefreshToken() called");
        Optional<RefreshToken> dbRefreshToken = refreshTokenDao.findRefreshTokenByRefreshToken(refreshToken);
        if (dbRefreshToken.isEmpty()) throw new JWTVerificationException("Invalid JWT Refresh Token");
        return dbRefreshToken.get();
    }

    @Override
    public RefreshToken createRefreshToken(String email) {
        log.info("RefreshTokenService: createRefreshToken() called");
        Optional<RefreshToken> dbRefreshToken = refreshTokenDao.findRefreshTokenByEmail(email);
        if (dbRefreshToken.isPresent())
            return refreshTokenDao.updateRefreshToken(dbRefreshToken.get());
        return refreshTokenDao.createRefreshToken(constructRefreshToken(email));
    }

    @Override
    public RefreshToken refreshToken(RefreshTokenRequest refreshTokenRequest) {
        log.info("RefreshTokenService: refreshToken() called");
        RefreshToken refreshToken = findRefreshTokenByRefreshToken(refreshTokenRequest.getRefreshToken());
        verifyRefreshTokenOwner(refreshToken, refreshTokenRequest);
        verifyRefreshTokenExpiration(refreshToken);
        refreshToken.setToken(generateToken());
        refreshToken.setExpiryDate(getRefreshTokenExpiryDate());
        refreshToken.setRefreshCount(refreshToken.getRefreshCount() + 1);
        return refreshTokenDao.updateRefreshToken(refreshToken);
    }

    @Override
    @Transactional
    public Boolean deleteRefreshToken(String email) {
        log.info("RefreshTokenService: deleteRefreshToken() called");
        return refreshTokenDao.deleteRefreshTokenByEmail(email);
    }

    private void verifyRefreshTokenExpiration(RefreshToken refreshToken) {
        log.info("RefreshTokenService: verifyRefreshTokenExpiration() called");
        if (refreshToken.getExpiryDate().compareTo(LocalDateTime.now()) < 0)
            throw new JWTVerificationException("Expired Refresh Token");
    }

    private void verifyRefreshTokenOwner(RefreshToken refreshToken, RefreshTokenRequest refreshTokenRequest) {
        log.info("RefreshTokenService: verifyRefreshTokenExpiration() called");
        if (!Objects.equals(refreshToken.getEmail(), refreshTokenRequest.getEmail()))
            throw new JWTVerificationException("Invalid Email");
    }

    private RefreshToken constructRefreshToken(String email) {
        log.info("RefreshTokenService: constructRefreshToken() called");
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(generateToken());
        refreshToken.setExpiryDate(getRefreshTokenExpiryDate());
        refreshToken.setRefreshCount(0L);
        refreshToken.setEmail(email);
        return refreshToken;
    }

    private LocalDateTime getRefreshTokenExpiryDate() {
        log.info("RefreshTokenService: getRefreshTokenExpiryDate() called");
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()
                + Long.parseLong(JWT_REFRESH_TOKEN_EXPIRATION_MS)), ZoneOffset.UTC);
    }

    private String generateToken() {
        log.info("RefreshTokenService: generateToken() called");
        return (UUID.randomUUID().toString() + UUID.randomUUID() + UUID.randomUUID() + UUID.randomUUID() + UUID.randomUUID());
    }
}
