package com.henry.bookrecommendationsystem.dto.base.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
    private String email;
    private String refreshToken;
}
