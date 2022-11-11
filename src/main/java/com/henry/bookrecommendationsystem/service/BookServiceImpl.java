package com.henry.bookrecommendationsystem.service;

import com.henry.bookrecommendationsystem.dao.BookDao;
import com.henry.bookrecommendationsystem.dto.BookDto;
import com.henry.bookrecommendationsystem.dto.BookFilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.dto.base.pagination.FilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.response.PaginationResponse;
import com.henry.bookrecommendationsystem.entity.Book;
import com.henry.bookrecommendationsystem.recommender.CollaborativeFilteringRecommender;
import com.henry.bookrecommendationsystem.transformer.BookTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
@Slf4j
@Service

public class BookServiceImpl implements BookService {
    private static final int MIN_OF_RECOMMENDED_BOOKS = 12;
    private final BookTransformer bookTransformer;
    private final BookDao bookDao;
    private final AuthorService authorService;
    private final UserService userService;
    private final UserReadingInfoService userReadingInfoService;
    private final CollaborativeFilteringRecommender collaborativeFilteringRecommender;

    public BookServiceImpl(BookTransformer bookTransformer, BookDao bookDao, AuthorService authorService, UserService userService, UserReadingInfoService userReadingInfoService, CollaborativeFilteringRecommender collaborativeFilteringRecommender) {
        this.bookTransformer = bookTransformer;
        this.bookDao = bookDao;
        this.userReadingInfoService = userReadingInfoService;
        this.authorService = authorService;
        this.userService = userService;
        this.collaborativeFilteringRecommender = collaborativeFilteringRecommender;
    }

    @Override
    public BookDao getDao() {
        return bookDao;
    }

    @Override
    public BookTransformer getTransformer() {
        return bookTransformer;
    }

    @Override
    public BookDto create(BookDto dto) {
        log.info("BookService: create() called");
        // set author entity before creating
        dto.setAuthor(authorService.findById(dto.getAuthor().getId()));
        return getTransformer().transformEntityToDto(getDao().create(getTransformer().transformDtoToEntity(dto)));
    }

    @Override
    public BookDto update(BookDto dto, Long id) {
        log.info("BookService: update() called");
        Optional<Book> book = getDao().findById(id);
        if (book.isEmpty())
            throw new EntityExistsException("Book not found for id: " + id);

        // set author entity before updating
        dto.setAuthor(authorService.findById(dto.getAuthor().getId()));
        getTransformer().updateEntity(dto, book.get());
        return getTransformer().transformEntityToDto(getDao().update(book.get()));
    }

    @Override
    public List<BookDto> findAllBooksByAuthorId(Long authorId) {
        log.info("BookService: findAllBooksByAuthorId() called");
        return getTransformer().transformEntityToDto(getDao().findAllBooksByAuthorId(authorId));
    }

    @Override
    public PaginationResponse<BookDto> findAllBooksPaginatedAndFiltered(FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest) {
        log.info("BookService: findAllBooksPaginatedAndFiltered() called");
        return buildPaginationResponse(getDao().findAllBooksPaginatedAndFiltered(bookFilterPaginationRequest));
    }

    @Override
    public List<BookDto> findAllRecommendedBooks() {
        log.info("BookService: findAllRecommendedBooks() called");
        UserDto currentUser = userService.getCurrentUser();
        List<Book> books = collaborativeFilteringRecommender.recommendedBooks(currentUser.getId());
        if (books.size() < MIN_OF_RECOMMENDED_BOOKS) {
            books.addAll(getDao().findAllBooksByCategoriesAndLimit(
                    userReadingInfoService.findUserReadingInfo().getUserBookCategories().stream().map(
                    userBookCategoryDto -> userBookCategoryDto.getCategory().getName()
            ).collect(Collectors.toList()), MIN_OF_RECOMMENDED_BOOKS - books.size()));
        }
        return getTransformer().transformEntityToDto(books);
    }
}
