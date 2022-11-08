package com.henry.bookrecommendationsystem.transformer;

import com.henry.bookrecommendationsystem.dto.AuthorDto;
import com.henry.bookrecommendationsystem.entity.Author;
import com.henry.bookrecommendationsystem.transformer.base.BaseTransformer;
import com.henry.bookrecommendationsystem.transformer.mapper.AuthorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
@Component
@AllArgsConstructor
public class AuthorTransformer implements BaseTransformer<Author, AuthorDto, AuthorMapper> {
    private final AuthorMapper authorMapper;

    @Override
    public AuthorMapper getMapper() {
        return authorMapper;
    }
}
