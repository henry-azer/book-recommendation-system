package com.henry.bookrecommendationsystem.service;

import com.henry.bookrecommendationsystem.dao.UserDao;
import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.entity.User;
import com.henry.bookrecommendationsystem.manager.JWTAuthenticationManager;
import com.henry.bookrecommendationsystem.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserTransformer userTransformer;
    private final UserDao userDao;
    private final JWTAuthenticationManager jwtAuthenticationManager;

    public UserServiceImpl(UserTransformer userTransformer, UserDao userDao, JWTAuthenticationManager jwtAuthenticationManager) {
        this.userTransformer = userTransformer;
        this.userDao = userDao;
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }

    @Override
    public UserDao getDao() {
        return userDao;
    }

    @Override
    public UserTransformer getTransformer() {
        return userTransformer;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        log.info("UserService: findUserByEmail() called");
        Optional<User> optionalUser = getDao().findUserByEmail(email);
        if (optionalUser.isEmpty())
            throw new EntityNotFoundException("User not found, email: " + email);
        return getTransformer().transformEntityToDto(optionalUser.get());
    }

    @Override
    public UserDto getCurrentUser() {
        log.info("UserService: getCurrentUser() called");
        return findUserByEmail(jwtAuthenticationManager.getCurrentUserEmail());
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        return getDao().isUserExistsByEmail(email);
    }
}
