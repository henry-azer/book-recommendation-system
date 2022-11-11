package com.henry.bookrecommendationsystem.transformer;

import com.henry.bookrecommendationsystem.dto.UserBookRateDto;
import com.henry.bookrecommendationsystem.entity.UserBookRate;
import com.henry.bookrecommendationsystem.transformer.base.BaseTransformer;
import com.henry.bookrecommendationsystem.transformer.mapper.UserBookRateMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 09/11/2022
 */
@Component
@AllArgsConstructor
public class UserBookRateTransformer implements BaseTransformer<UserBookRate, UserBookRateDto, UserBookRateMapper> {
    private final UserBookRateMapper userBookRateMapper;

    @Override
    public UserBookRateMapper getMapper() {
        return userBookRateMapper;
    }
}
