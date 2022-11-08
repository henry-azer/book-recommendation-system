package com.henry.bookrecommendationsystem.transformer.mapper;

import com.henry.bookrecommendationsystem.dto.BookDto;
import com.henry.bookrecommendationsystem.entity.Book;
import com.henry.bookrecommendationsystem.transformer.mapper.base.BaseMapper;
import com.henry.bookrecommendationsystem.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface BookMapper extends BaseMapper<Book, BookDto> {
}
