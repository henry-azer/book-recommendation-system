package com.henry.bookrecommendationsystem.security;

import com.henry.bookrecommendationsystem.security.jwt.JWTAuthenticationEntryPoint;
import com.henry.bookrecommendationsystem.security.jwt.JWTRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfigurations {
    private final JWTRequestFilter jwtRequestFilter;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable().httpBasic().disable().cors()
                .and().authorizeHttpRequests()
                .antMatchers("/api/user/register").permitAll()
                .antMatchers("/api/auth/log-in").permitAll()
                .antMatchers("/book-service/api/**").authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected CorsConfigurationSource CorsConfigurationSource() {
        return request -> {
            CorsConfiguration corsConfig = new CorsConfiguration();

            corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
            corsConfig.setExposedHeaders(Collections.singletonList("Authorization"));
            corsConfig.setAllowedHeaders(Collections.singletonList("*"));
            corsConfig.setAllowedMethods(Collections.singletonList("*"));
            corsConfig.setAllowCredentials(true);
            corsConfig.setMaxAge(3600L);

            return corsConfig;
        };
    }
}