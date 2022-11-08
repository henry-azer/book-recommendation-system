package com.henry.bookrecommendationsystem.controller;

import com.henry.bookrecommendationsystem.controller.base.BaseController;
import com.henry.bookrecommendationsystem.dto.BookDto;
import com.henry.bookrecommendationsystem.dto.BookFilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.pagination.FilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.response.ApiResponse;
import com.henry.bookrecommendationsystem.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/book")
public class BookController implements BaseController<BookService> {
    private final BookService bookService;

    @Override
    public BookService getService() {
        return bookService;
    }

    @GetMapping("/find-by-id/{bookId}")
    public ApiResponse findBookByBookId(@PathVariable Long bookId) {
        log.info("BookController: findBookByBookId() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Book fetched successfully.", getService().findById(bookId));
    }

    @GetMapping("/find-all-by-author-id/{authorId}")
    public ApiResponse findAllBooksByAuthorId(@PathVariable Long authorId) {
        log.info("BookController: findAllBooksByAuthorId() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Books of author fetched successfully.", getService().findAllBooksByAuthorId(authorId));
    }

    @GetMapping("/find-all-categories")
    public ApiResponse getBookCategories() {
        log.info("BookController: getBookCategories() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Book categories fetched successfully.", getService().getBookCategories());
    }

    @PostMapping("/find-all-paginated-filtered")
    public ApiResponse findAllBooksPaginatedAndFiltered(@RequestBody FilterPaginationRequest<BookFilterPaginationRequest> bookFilterPaginationRequest) {
        log.info("BookController: findAllBooksPaginatedAndFiltered() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Books paginated filtered fetched successfully.", getService().findAllBooksPaginatedAndFiltered(bookFilterPaginationRequest));
    }

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse createBook(@RequestBody BookDto bookDto) {
        log.info("BookController: createBook() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Book created successfully.", getService().create(bookDto));
    }

    @PutMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse updateBook(@RequestBody BookDto bookDto) {
        log.info("BookController: updateBook() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Book updated successfully.", getService().update(bookDto, bookDto.getId()));
    }
}
