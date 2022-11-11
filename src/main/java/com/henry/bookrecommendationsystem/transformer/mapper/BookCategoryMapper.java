package com.henry.bookrecommendationsystem.transformer.mapper;

import com.henry.bookrecommendationsystem.dto.BookCategoryDto;
import com.henry.bookrecommendationsystem.entity.BookCategory;
import com.henry.bookrecommendationsystem.transformer.mapper.base.BaseMapper;
import com.henry.bookrecommendationsystem.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface BookCategoryMapper extends BaseMapper<BookCategory, BookCategoryDto> {
}
