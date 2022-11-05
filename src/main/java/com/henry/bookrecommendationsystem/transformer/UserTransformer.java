package com.henry.bookrecommendationsystem.transformer;

import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.entity.User;
import com.henry.bookrecommendationsystem.transformer.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTransformer implements BaseTransformer<User, UserDto, UserMapper> {
    private final UserMapper userMapper;

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }
}
