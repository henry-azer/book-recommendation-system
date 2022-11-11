package com.henry.bookrecommendationsystem.service;

import com.henry.bookrecommendationsystem.dao.BookCategoryDao;
import com.henry.bookrecommendationsystem.transformer.BookCategoryTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Slf4j
@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryDao bookCategoryDao;
    private final BookCategoryTransformer bookCategoryTransformer;

    public BookCategoryServiceImpl(BookCategoryDao bookCategoryDao, BookCategoryTransformer bookCategoryTransformer) {
        this.bookCategoryDao = bookCategoryDao;
        this.bookCategoryTransformer = bookCategoryTransformer;
    }

    @Override
    public BookCategoryDao getDao() {
        return bookCategoryDao;
    }

    @Override
    public BookCategoryTransformer getTransformer() {
        return bookCategoryTransformer;
    }
}
