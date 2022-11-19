package com.henry.bookrecommendationsystem.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
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

    public String generateAccessToken(String email) throws JWTCreationException {
//        return JWT.create().withSubject("Library Authentication").withIssuer("Henry").withIssuedAt(new Date())
//                .withExpiresAt(getAccessTokenExpireDate()).withClaim("email", email).sign(Algorithm.HMAC256(JWT_AUTHENTICATION_SECRET));
        return JWT.create().withSubject("Library Authentication").withIssuer("Henry").withIssuedAt(new Date())
                .withClaim("email", email).sign(Algorithm.HMAC256(JWT_AUTHENTICATION_SECRET));
    }

    public String getAccessTokenUserEmail(String accessToken) {
        return JWT.require(Algorithm.HMAC256(JWT_AUTHENTICATION_SECRET))
                .withSubject("Library Authentication").withIssuer("Henry")
                .build().verify(accessToken).getClaim("email").asString();
    }

    public void verifyAccessTokenExpiration(String accessToken) {
        DecodedJWT jwtToken = JWT.decode(accessToken);
        if (jwtToken.getExpiresAt().before(new Date()))
            throw new JWTVerificationException("Expired Access Token");
    }

    private Date getAccessTokenExpireDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MILLISECOND, Integer.parseInt(JWT_ACCESS_TOKEN_EXPIRATION_MS));
        return cal.getTime();
    }
}