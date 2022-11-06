package com.henry.bookrecommendationsystem.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@Component
public class JWTAuthenticationUtil {

    @Value("${library.security.jwtSecret}")
    private String JWT_AUTHENTICATION_SECRET;

    @Value("${library.security.jwt.accessToken.expirationMs}")
    private String JWT_ACCESS_TOKEN_EXPIRATION_MS;

    public String generateAccessToken(String email) throws IllegalArgumentException, JWTCreationException {
        return JWT.create().withSubject("Library Authentication").withIssuer("Henry").withIssuedAt(new Date())
                .withExpiresAt(Instant.now().plusMillis(Long.parseLong(JWT_ACCESS_TOKEN_EXPIRATION_MS)))
                .withClaim("email", email).sign(Algorithm.HMAC256(JWT_AUTHENTICATION_SECRET));
    }

    public String validateTokenAndRetrieveUserEmail(String token) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC256(JWT_AUTHENTICATION_SECRET)).acceptExpiresAt(Long.parseLong(JWT_ACCESS_TOKEN_EXPIRATION_MS))
                .withSubject("Library Authentication").withIssuer("Henry").build().verify(token).getClaim("email").asString();
    }
}