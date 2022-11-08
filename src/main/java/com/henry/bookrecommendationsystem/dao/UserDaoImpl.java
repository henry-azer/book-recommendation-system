package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.entity.User;
import com.henry.bookrecommendationsystem.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@Component
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        return getRepository().existsByEmail(email);
    }
}
