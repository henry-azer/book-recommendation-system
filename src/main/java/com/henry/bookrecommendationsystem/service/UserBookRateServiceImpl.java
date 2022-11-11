package com.henry.bookrecommendationsystem.service;

import com.henry.bookrecommendationsystem.dao.UserBookRateDao;
import com.henry.bookrecommendationsystem.dto.BookDto;
import com.henry.bookrecommendationsystem.dto.UserBookRateDto;
import com.henry.bookrecommendationsystem.entity.UserBookRate;
import com.henry.bookrecommendationsystem.transformer.UserBookRateTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 09/11/2022
 */
@Slf4j
@Service
public class UserBookRateServiceImpl implements UserBookRateService {
    private final UserBookRateTransformer userBookRateTransformer;
    private final UserBookRateDao userBookRateDao;
    private final UserService userService;
    private final BookService bookService;

    public UserBookRateServiceImpl(UserBookRateTransformer userBookRateTransformer, UserBookRateDao userBookRateDao, UserService userService, BookService bookService) {
        this.userBookRateTransformer = userBookRateTransformer;
        this.userBookRateDao = userBookRateDao;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public UserBookRateDao getDao() {
        return userBookRateDao;
    }

    @Override
    public UserBookRateTransformer getTransformer() {
        return userBookRateTransformer;
    }

    @Override
    @Transactional
    public UserBookRateDto create(UserBookRateDto dto) {
        log.info("UserBookRateService: create() called");
        dto.setUser(userService.findById(userService.getCurrentUser().getId()));
        BookDto bookDto = bookService.findById(dto.getBook().getId());
        BookDto updateBook = updateBookRate(bookDto, dto);
        dto.setBook(updateBook);
        return getTransformer().transformEntityToDto(getDao().create(getTransformer().transformDtoToEntity(dto)));
    }

    @Override
    @Transactional
    public UserBookRateDto update(UserBookRateDto dto, Long id) {
        log.info("UserBookRateService: update() called");
        Optional<UserBookRate> entity = getDao().findById(id);
        if (entity.isEmpty()) throw new EntityNotFoundException("User doesn't rate this book!");
        getTransformer().updateEntity(dto, entity.get());
        BookDto bookDto = bookService.findById(dto.getBook().getId());
        BookDto updateBook = updateBookRate(bookDto, dto);
        dto.setBook(updateBook);
        return getTransformer().transformEntityToDto(getDao().update(entity.get()));
    }

    @Override
    public UserBookRateDto rateBook(UserBookRateDto userBookRateDto) {
        log.info("UserBookRateService: rateBook() called");
        Optional<UserBookRate> userBookRate = getDao().findUserBookRateByUserIdAndBookId(userService.getCurrentUser().getId(), userBookRateDto.getBook().getId());
        if (userBookRate.isPresent()) {
            userBookRateDto.setId(userBookRate.get().getId());
            return update(userBookRateDto, userBookRateDto.getId());
        }
        return create(userBookRateDto);
    }

    private BookDto updateBookRate(BookDto bookDto, UserBookRateDto userBookRateDto) {
        bookDto.setUsersRateCount(bookDto.getUsersRateCount() + 1);
        bookDto.setRate(bookDto.getRate() + userBookRateDto.getRate());
        return bookService.update(bookDto, bookDto.getId());
    }
}
