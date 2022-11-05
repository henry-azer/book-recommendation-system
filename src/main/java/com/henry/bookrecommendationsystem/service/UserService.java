package com.henry.bookrecommendationsystem.service;


import com.henry.bookrecommendationsystem.dao.UserDao;
import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.entity.User;
import com.henry.bookrecommendationsystem.transformer.UserTransformer;

public interface UserService extends BaseService<User, UserDto, UserDao, UserTransformer> {
    UserDto findUserByEmail(String email);

    UserDto getCurrentUser();

    Boolean isUserExistsByEmail(String email);
}
