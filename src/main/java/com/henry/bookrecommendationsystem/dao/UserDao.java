package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.dao.base.BaseDao;
import com.henry.bookrecommendationsystem.entity.User;
import com.henry.bookrecommendationsystem.repository.UserRepository;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
public interface UserDao extends BaseDao<User, UserRepository> {
    Optional<User> findUserByEmail(String email);

    Boolean isUserExistsByEmail(String email);
}
