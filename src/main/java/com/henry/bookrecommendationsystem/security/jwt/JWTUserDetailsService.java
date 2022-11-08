package com.henry.bookrecommendationsystem.security.jwt;

import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Slf4j
@Service
public class JWTUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public JWTUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("UserDetailsService: loadUserByUsername() called");
        UserDto userDto = userService.findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(userDto.getEmail(),
                userDto.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}