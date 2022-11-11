package com.henry.bookrecommendationsystem.service;

import com.henry.bookrecommendationsystem.dao.BookDao;
import com.henry.bookrecommendationsystem.dto.BookDto;
import com.henry.bookrecommendationsystem.dto.BookFilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.pagination.FilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.response.PaginationResponse;
import com.henry.bookrecommendationsystem.entity.Book;
import com.henry.bookrecommendationsystem.service.base.BaseService;
import com.henry.bookrecommendationsystem.transformer.BookTransformer;

import java.util.List;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
public interface BookService extends BaseService<Book, BookDto, BookDao, BookTransformer> {
    List<BookDto> findAllBooksByAuthorId(Long authorId);

    PaginationResponse<BookDto> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest);

    List<BookDto> findAllRecommendedBooks();
}
