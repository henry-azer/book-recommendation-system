package com.henry.bookrecommendationsystem.transformer;

import com.henry.bookrecommendationsystem.dto.UserReadingInfoDto;
import com.henry.bookrecommendationsystem.entity.UserReadingInfo;
import com.henry.bookrecommendationsystem.transformer.base.BaseTransformer;
import com.henry.bookrecommendationsystem.transformer.mapper.UserReadingInfoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Component
@AllArgsConstructor
public class UserReadingInfoTransformer implements BaseTransformer<UserReadingInfo, UserReadingInfoDto, UserReadingInfoMapper> {
    private final UserReadingInfoMapper userReadingInfoMapper;

    @Override
    public UserReadingInfoMapper getMapper() {
        return userReadingInfoMapper;
    }
}
