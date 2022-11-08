package com.henry.bookrecommendationsystem.transformer;

import com.henry.bookrecommendationsystem.dto.BookDto;
import com.henry.bookrecommendationsystem.entity.Book;
import com.henry.bookrecommendationsystem.transformer.base.BaseTransformer;
import com.henry.bookrecommendationsystem.transformer.mapper.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
@Component
@AllArgsConstructor
public class BookTransformer implements BaseTransformer<Book, BookDto, BookMapper> {
    private final BookMapper bookMapper;

    @Override
    public BookMapper getMapper() {
        return bookMapper;
    }
}
