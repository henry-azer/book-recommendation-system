package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.dto.BookFilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.pagination.FilterPaginationRequest;
import com.henry.bookrecommendationsystem.entity.Book;
import com.henry.bookrecommendationsystem.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
@Component
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookRepository getRepository() {
        return bookRepository;
    }

    @Override
    public List<Book> findAllBooksByAuthorId(Long authorId) {
        return getRepository().findAllByAuthorIdAndMarkedAsDeletedFalse(authorId);
    }

    @Override
    public List<Book> findAllBooksByCategoriesAndLimit(List<String> categories, Integer limit) {
        List<Book> books = getRepository().findAllByCategoryNameInAndMarkedAsDeletedFalse(categories);
        if (books.size() <= limit) return books;
        return books.subList(0, limit);
    }

    @Override
    public Page<Book> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest) {
        PageRequest pageRequest = getPageRequest(bookFilterPaginationRequest);
        BookFilterPaginationRequest criteria = bookFilterPaginationRequest.getCriteria();
        if (criteria == null)
            return getRepository().findAll(pageRequest, bookFilterPaginationRequest.getDeletedRecords());
        return getRepository().findAllBooksPaginatedAndFiltered(criteria.getName(), criteria.getCategories(),
                criteria.getFromPrice(), criteria.getToPrice(), criteria.getFromPagesNumber(), criteria.getToPagesNumber(),
                criteria.getFromReadingDuration(), criteria.getToReadingDuration(), bookFilterPaginationRequest.getDeletedRecords(), pageRequest);
    }

    private PageRequest getPageRequest(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest) {
        if (bookFilterPaginationRequest.getPageSize() == -1) bookFilterPaginationRequest.setPageSize(Integer.MAX_VALUE);
        return PageRequest.of(bookFilterPaginationRequest.getPageNumber() - 1, bookFilterPaginationRequest.getPageSize(), buildSort(bookFilterPaginationRequest, Book.class));
    }
}
