package com.henry.bookrecommendationsystem.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.bookrecommendationsystem.dto.base.response.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@Component
@AllArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {
    private final JWTAuthenticationUtil jwtAuthenticationUtil;
    private final JWTUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            if (jwt.isBlank()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                OutputStream outputStream = response.getOutputStream();
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(outputStream, new ApiResponse(false, LocalDateTime.now().toString(), "Invalid JWT Token in Bearer Header", null));
                outputStream.flush();
            } else {
                try {
                    String email = jwtAuthenticationUtil.validateTokenAndRetrieveUserEmail(jwt);
                    UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());

                    if (SecurityContextHolder.getContext().getAuthentication() == null)
                        SecurityContextHolder.getContext().setAuthentication(authToken);

                } catch (JWTVerificationException jwtVerificationException) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    OutputStream outputStream = response.getOutputStream();
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writeValue(outputStream, new ApiResponse(false,
                            LocalDateTime.now().toString(), jwtVerificationException.getMessage(), null));
                    outputStream.flush();
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}