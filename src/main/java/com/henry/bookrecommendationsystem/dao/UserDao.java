package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.entity.User;
import com.henry.bookrecommendationsystem.repository.UserRepository;

import java.util.Optional;

public interface UserDao extends BaseDao<User, UserRepository> {
    Optional<User> findUserByEmail(String email);

    Boolean isUserExistsByEmail(String email);
}
