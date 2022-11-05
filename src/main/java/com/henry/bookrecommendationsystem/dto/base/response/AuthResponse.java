package com.henry.bookrecommendationsystem.dto.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private Long accessTokenExpireDate;
    private String refreshToken;
    private Long refreshTokenExpireDate;
}
