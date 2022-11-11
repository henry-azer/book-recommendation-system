package com.henry.bookrecommendationsystem.transformer;

import com.henry.bookrecommendationsystem.dto.UserBookCategoryDto;
import com.henry.bookrecommendationsystem.entity.UserBookCategory;
import com.henry.bookrecommendationsystem.transformer.base.BaseTransformer;
import com.henry.bookrecommendationsystem.transformer.mapper.UserBookCategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Component
@AllArgsConstructor
public class UserBookCategoryTransformer implements BaseTransformer<UserBookCategory, UserBookCategoryDto, UserBookCategoryMapper> {
    private final UserBookCategoryMapper userBookCategoryMapper;

    @Override
    public UserBookCategoryMapper getMapper() {
        return userBookCategoryMapper;
    }
}
