package com.henry.bookrecommendationsystem.controller;

import com.henry.bookrecommendationsystem.dto.base.request.AuthRequest;
import com.henry.bookrecommendationsystem.dto.base.request.RefreshTokenRequest;
import com.henry.bookrecommendationsystem.dto.base.response.ApiResponse;
import com.henry.bookrecommendationsystem.manager.JWTAuthenticationManager;
import com.henry.bookrecommendationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final JWTAuthenticationManager jwtAuthenticationManager;
    private final UserService userService;

    @PostMapping("/log-in")
    public ApiResponse login(@Valid @RequestBody AuthRequest authRequest) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Logged in successfully.", jwtAuthenticationManager.login(authRequest));
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ApiResponse currentLoggedUser() {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Request done successfully.", userService.getCurrentUser());
    }

    @PostMapping("/refresh-token")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ApiResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Token Refreshed successfully.", jwtAuthenticationManager.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/log-out")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ApiResponse logout() {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Logged out successfully.", jwtAuthenticationManager.logout());
    }
}
