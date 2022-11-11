package com.henry.bookrecommendationsystem.controller;

import com.henry.bookrecommendationsystem.dao.*;
import com.henry.bookrecommendationsystem.dto.AuthorDto;
import com.henry.bookrecommendationsystem.dto.BookCategoryDto;
import com.henry.bookrecommendationsystem.dto.BookDto;
import com.henry.bookrecommendationsystem.dto.UserBookRateDto;
import com.henry.bookrecommendationsystem.dto.base.response.ApiResponse;
import com.henry.bookrecommendationsystem.entity.Author;
import com.henry.bookrecommendationsystem.entity.Book;
import com.henry.bookrecommendationsystem.entity.BookCategory;
import com.henry.bookrecommendationsystem.enums.UserGender;
import com.henry.bookrecommendationsystem.manager.JWTAuthenticationManagerImpl;
import com.henry.bookrecommendationsystem.repository.*;
import com.henry.bookrecommendationsystem.security.jwt.JWTAuthenticationUtil;
import com.henry.bookrecommendationsystem.service.*;
import com.henry.bookrecommendationsystem.transformer.*;
import com.henry.bookrecommendationsystem.transformer.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Henry Azer
 * @since 11/11/2022
 */
@Slf4j
class BookControllerTest {

    @Test
    void testGetService() {
        log.info("BookControllerTest: testGetService() called");
        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao = new BookDaoImpl(mock(BookRepository.class));
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorService = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));

        UserReadingInfoDaoImpl userReadingInfoDao = new UserReadingInfoDaoImpl(mock(UserReadingInfoRepository.class));
        UserReadingInfoTransformer userReadingInfoTransformer = new UserReadingInfoTransformer(
                new UserReadingInfoMapperImpl());
        UserServiceImpl userService = new UserServiceImpl(null, null, null, new Argon2PasswordEncoder());

        BookServiceImpl bookServiceImpl = new BookServiceImpl(bookTransformer, bookDao, authorService,
                new UserReadingInfoServiceImpl(userReadingInfoDao, userReadingInfoTransformer, userService,
                        new UserBookCategoryServiceImpl(null, null, null, null)));

        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        BookCategoryServiceImpl bookCategoryService = new BookCategoryServiceImpl(bookCategoryDao,
                new BookCategoryTransformer(new BookCategoryMapperImpl()));

        UserBookRateTransformer userBookRateTransformer = new UserBookRateTransformer(new UserBookRateMapperImpl());
        UserBookRateDaoImpl userBookRateDao = new UserBookRateDaoImpl(mock(UserBookRatingRepository.class));
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        UserServiceImpl userService1 = new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());

        BookTransformer bookTransformer1 = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao1 = new BookDaoImpl(mock(BookRepository.class));
        AuthorServiceImpl authorService1 = new AuthorServiceImpl(null, null);

        assertSame(bookServiceImpl,
                (new BookController(bookServiceImpl, bookCategoryService,
                        new UserBookRateServiceImpl(userBookRateTransformer, userBookRateDao, userService1, new BookServiceImpl(
                                bookTransformer1, bookDao1, authorService1, new UserReadingInfoServiceImpl(null, null, null, null)))))
                        .getService());
        log.info("BookControllerTest: testGetService() ended");
    }

    @Test
    void testFindBookByBookId() {
        log.info("BookControllerTest: testFindBookByBookId() called");
        Author author = new Author();
        author.setAge(1);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        author.setBirthdate(fromResult);
        author.setCountry("GB");
        author.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        author.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        author.setDeathdate(fromResult1);
        author.setDescription("The characteristics of someone or something");
        author.setGender(UserGender.MALE);
        author.setId(123L);
        author.setImageUrl("https://example.org/example");
        author.setMarkedAsDeleted(true);
        author.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        author.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        author.setName("Name");

        BookCategory bookCategory = new BookCategory();
        bookCategory.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        bookCategory.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        bookCategory.setDescription("The characteristics of someone or something");
        bookCategory.setId(123L);
        bookCategory.setMarkedAsDeleted(true);
        bookCategory.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        bookCategory.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        bookCategory.setName("Name");

        Book book = new Book();
        book.setAuthor(author);
        book.setCategory(bookCategory);
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setMarkedAsDeleted(true);
        book.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setName("Name");
        book.setPagesNumber(10);
        book.setPrice(10.0d);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        book.setPublishDate(fromResult2);
        book.setRate(10.0d);
        book.setReadingDuration(1);
        book.setUsersRateCount(3L);
        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.findById((Long) any())).thenReturn(Optional.of(book));
        BookDaoImpl bookDao = new BookDaoImpl(bookRepository);
        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorService = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));

        UserReadingInfoDaoImpl userReadingInfoDao = new UserReadingInfoDaoImpl(mock(UserReadingInfoRepository.class));
        UserReadingInfoTransformer userReadingInfoTransformer = new UserReadingInfoTransformer(
                new UserReadingInfoMapperImpl());
        UserServiceImpl userService = new UserServiceImpl(null, null, null, new Argon2PasswordEncoder());

        BookServiceImpl bookService = new BookServiceImpl(bookTransformer, bookDao, authorService,
                new UserReadingInfoServiceImpl(userReadingInfoDao, userReadingInfoTransformer, userService,
                        new UserBookCategoryServiceImpl(null, null, null, null)));

        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        BookCategoryServiceImpl bookCategoryService = new BookCategoryServiceImpl(bookCategoryDao,
                new BookCategoryTransformer(new BookCategoryMapperImpl()));

        UserBookRateTransformer userBookRateTransformer = new UserBookRateTransformer(new UserBookRateMapperImpl());
        UserBookRateDaoImpl userBookRateDao = new UserBookRateDaoImpl(mock(UserBookRatingRepository.class));
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        UserServiceImpl userService1 = new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());

        BookTransformer bookTransformer1 = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao1 = new BookDaoImpl(mock(BookRepository.class));
        AuthorServiceImpl authorService1 = new AuthorServiceImpl(null, null);

        ApiResponse actualFindBookByBookIdResult = (new BookController(bookService, bookCategoryService,
                new UserBookRateServiceImpl(userBookRateTransformer, userBookRateDao, userService1, new BookServiceImpl(
                        bookTransformer1, bookDao1, authorService1, new UserReadingInfoServiceImpl(null, null, null, null)))))
                .findBookByBookId(1L);
        Object body = actualFindBookByBookIdResult.getBody();
        assertTrue(body instanceof BookDto);
        assertTrue(actualFindBookByBookIdResult.getSuccess());
        assertEquals("Book fetched successfully.", actualFindBookByBookIdResult.getMessage());
        assertEquals(1, ((BookDto) body).getReadingDuration().intValue());
        assertEquals(10.0d, ((BookDto) body).getRate().doubleValue());
        assertSame(fromResult2, ((BookDto) body).getPublishDate());
        assertEquals(10.0d, ((BookDto) body).getPrice().doubleValue());
        assertEquals(10, ((BookDto) body).getPagesNumber().intValue());
        assertEquals("Name", ((BookDto) body).getName());
        assertTrue(((BookDto) body).getMarkedAsDeleted());
        assertEquals("https://example.org/example", ((BookDto) body).getImageUrl());
        assertEquals("01:01", ((BookDto) body).getLastModificationDate().toLocalTime().toString());
        assertEquals(123L, ((BookDto) body).getId().longValue());
        assertEquals("The characteristics of someone or something", ((BookDto) body).getDescription());
        assertEquals("01:01", ((BookDto) body).getCreationDate().toLocalTime().toString());
        assertEquals(3L, ((BookDto) body).getUsersRateCount().longValue());
        BookCategoryDto category = ((BookDto) body).getCategory();
        assertEquals(123L, category.getId().longValue());
        AuthorDto author1 = ((BookDto) body).getAuthor();
        assertEquals("Name", author1.getName());
        assertTrue(author1.getMarkedAsDeleted());
        assertEquals("https://example.org/example", author1.getImageUrl());
        assertEquals(123L, author1.getId().longValue());
        assertEquals(UserGender.MALE, author1.getGender());
        assertEquals("The characteristics of someone or something", author1.getDescription());
        assertSame(fromResult1, author1.getDeathdate());
        assertEquals("GB", author1.getCountry());
        assertSame(fromResult, author1.getBirthdate());
        assertEquals(1, author1.getAge().intValue());
        assertEquals("The characteristics of someone or something", category.getDescription());
        assertEquals("Name", category.getName());
        assertTrue(category.getMarkedAsDeleted());
        verify(bookRepository).findById(any());
        log.info("BookControllerTest: testFindBookByBookId() ended");
    }

    @Test
    void testFindAllRecommendBooks() {
        log.info("BookControllerTest: testFindAllRecommendBooks() ended");
        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao = new BookDaoImpl(mock(BookRepository.class));
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorService = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));

        UserReadingInfoDaoImpl userReadingInfoDao = new UserReadingInfoDaoImpl(mock(UserReadingInfoRepository.class));
        UserReadingInfoTransformer userReadingInfoTransformer = new UserReadingInfoTransformer(
                new UserReadingInfoMapperImpl());
        UserServiceImpl userService = new UserServiceImpl(null, null, null, new Argon2PasswordEncoder());

        new BookServiceImpl(bookTransformer, bookDao, authorService,
                new UserReadingInfoServiceImpl(userReadingInfoDao, userReadingInfoTransformer, userService,
                        new UserBookCategoryServiceImpl(null, null, null, null)));

        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        new BookCategoryServiceImpl(bookCategoryDao,
                new BookCategoryTransformer(new BookCategoryMapperImpl()));

        new UserBookRateTransformer(new UserBookRateMapperImpl());
        new UserBookRateDaoImpl(mock(UserBookRatingRepository.class));
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());
        new BookTransformer(new BookMapperImpl());
        new BookDaoImpl(mock(BookRepository.class));
        new AuthorServiceImpl(null, null);
        log.info("BookControllerTest: testFindAllRecommendBooks() ended");
    }

    @Test
    void testFindAllBooksByAuthorId() {
        log.info("BookControllerTest: testFindAllBooksByAuthorId() called");
        BookRepository bookRepository = mock(BookRepository.class);
        ArrayList<Book> bookList = new ArrayList<>();
        when(bookRepository.findAllByAuthorIdAndMarkedAsDeletedFalse((Long) any())).thenReturn(bookList);
        BookDaoImpl bookDao = new BookDaoImpl(bookRepository);
        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorService = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));

        UserReadingInfoDaoImpl userReadingInfoDao = new UserReadingInfoDaoImpl(mock(UserReadingInfoRepository.class));
        UserReadingInfoTransformer userReadingInfoTransformer = new UserReadingInfoTransformer(
                new UserReadingInfoMapperImpl());
        UserServiceImpl userService = new UserServiceImpl(null, null, null, new Argon2PasswordEncoder());

        BookServiceImpl bookService = new BookServiceImpl(bookTransformer, bookDao, authorService,
                new UserReadingInfoServiceImpl(userReadingInfoDao, userReadingInfoTransformer, userService,
                        new UserBookCategoryServiceImpl(null, null, null, null)));

        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        BookCategoryServiceImpl bookCategoryService = new BookCategoryServiceImpl(bookCategoryDao,
                new BookCategoryTransformer(new BookCategoryMapperImpl()));

        UserBookRateTransformer userBookRateTransformer = new UserBookRateTransformer(new UserBookRateMapperImpl());
        UserBookRateDaoImpl userBookRateDao = new UserBookRateDaoImpl(mock(UserBookRatingRepository.class));
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        UserServiceImpl userService1 = new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());

        BookTransformer bookTransformer1 = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao1 = new BookDaoImpl(mock(BookRepository.class));
        AuthorServiceImpl authorService1 = new AuthorServiceImpl(null, null);

        ApiResponse actualFindAllBooksByAuthorIdResult = (new BookController(bookService, bookCategoryService,
                new UserBookRateServiceImpl(userBookRateTransformer, userBookRateDao, userService1, new BookServiceImpl(
                        bookTransformer1, bookDao1, authorService1, new UserReadingInfoServiceImpl(null, null, null, null)))))
                .findAllBooksByAuthorId(1L);
        assertEquals(bookList, actualFindAllBooksByAuthorIdResult.getBody());
        assertTrue(actualFindAllBooksByAuthorIdResult.getSuccess());
        assertEquals("Books of author fetched successfully.", actualFindAllBooksByAuthorIdResult.getMessage());
        verify(bookRepository).findAllByAuthorIdAndMarkedAsDeletedFalse(any());
        log.info("BookControllerTest: testFindAllBooksByAuthorId() ended");
    }

    @Test
    void testGetBookCategories() {
        log.info("BookControllerTest: testGetBookCategories() called");
        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        ArrayList<BookCategory> bookCategoryList = new ArrayList<>();
        when(bookCategoryDao.findAll()).thenReturn(bookCategoryList);
        BookCategoryServiceImpl bookCategoryService = new BookCategoryServiceImpl(bookCategoryDao,
                new BookCategoryTransformer(new BookCategoryMapperImpl()));

        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao = new BookDaoImpl(mock(BookRepository.class));
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorService = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));

        UserReadingInfoDaoImpl userReadingInfoDao = new UserReadingInfoDaoImpl(mock(UserReadingInfoRepository.class));
        UserReadingInfoTransformer userReadingInfoTransformer = new UserReadingInfoTransformer(
                new UserReadingInfoMapperImpl());
        UserServiceImpl userService = new UserServiceImpl(null, null, null, new Argon2PasswordEncoder());

        BookServiceImpl bookService = new BookServiceImpl(bookTransformer, bookDao, authorService,
                new UserReadingInfoServiceImpl(userReadingInfoDao, userReadingInfoTransformer, userService,
                        new UserBookCategoryServiceImpl(null, null, null, null)));

        UserBookRateTransformer userBookRateTransformer = new UserBookRateTransformer(new UserBookRateMapperImpl());
        UserBookRateDaoImpl userBookRateDao = new UserBookRateDaoImpl(mock(UserBookRatingRepository.class));
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        UserServiceImpl userService1 = new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());

        BookTransformer bookTransformer1 = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao1 = new BookDaoImpl(mock(BookRepository.class));
        AuthorServiceImpl authorService1 = new AuthorServiceImpl(null, null);

        ApiResponse actualBookCategories = (new BookController(bookService, bookCategoryService,
                new UserBookRateServiceImpl(userBookRateTransformer, userBookRateDao, userService1, new BookServiceImpl(
                        bookTransformer1, bookDao1, authorService1, new UserReadingInfoServiceImpl(null, null, null, null)))))
                .getBookCategories();
        assertEquals(bookCategoryList, actualBookCategories.getBody());
        assertTrue(actualBookCategories.getSuccess());
        assertEquals("Book categories fetched successfully.", actualBookCategories.getMessage());
        verify(bookCategoryDao).findAll();
        log.info("BookControllerTest: testGetBookCategories() ended");
    }

    @Test
    void testFindAllBooksPaginatedAndFiltered() {
        log.info("BookControllerTest: testFindAllBooksPaginatedAndFiltered() called");
        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao = new BookDaoImpl(mock(BookRepository.class));
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorService = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));

        UserReadingInfoDaoImpl userReadingInfoDao = new UserReadingInfoDaoImpl(mock(UserReadingInfoRepository.class));
        UserReadingInfoTransformer userReadingInfoTransformer = new UserReadingInfoTransformer(
                new UserReadingInfoMapperImpl());
        UserServiceImpl userService = new UserServiceImpl(null, null, null, new Argon2PasswordEncoder());

        BookServiceImpl bookService = new BookServiceImpl(bookTransformer, bookDao, authorService,
                new UserReadingInfoServiceImpl(userReadingInfoDao, userReadingInfoTransformer, userService,
                        new UserBookCategoryServiceImpl(null, null, null, null)));

        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        BookCategoryServiceImpl bookCategoryService = new BookCategoryServiceImpl(bookCategoryDao,
                new BookCategoryTransformer(new BookCategoryMapperImpl()));

        UserBookRateTransformer userBookRateTransformer = new UserBookRateTransformer(new UserBookRateMapperImpl());
        UserBookRateDaoImpl userBookRateDao = new UserBookRateDaoImpl(mock(UserBookRatingRepository.class));
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        UserServiceImpl userService1 = new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());

        BookTransformer bookTransformer1 = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao1 = new BookDaoImpl(mock(BookRepository.class));
        AuthorServiceImpl authorService1 = new AuthorServiceImpl(null, null);

        new BookController(bookService, bookCategoryService,
                new UserBookRateServiceImpl(userBookRateTransformer, userBookRateDao, userService1, new BookServiceImpl(
                        bookTransformer1, bookDao1, authorService1, new UserReadingInfoServiceImpl(null, null, null, null))));
        log.info("BookControllerTest: testFindAllBooksPaginatedAndFiltered() ended");
    }

    @Test
    void testCreateBook() {
        log.info("BookControllerTest: testCreateBook() called");
        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao = new BookDaoImpl(mock(BookRepository.class));
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorService = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));

        UserReadingInfoDaoImpl userReadingInfoDao = new UserReadingInfoDaoImpl(mock(UserReadingInfoRepository.class));
        UserReadingInfoTransformer userReadingInfoTransformer = new UserReadingInfoTransformer(
                new UserReadingInfoMapperImpl());
        UserServiceImpl userService = new UserServiceImpl(null, null, null, new Argon2PasswordEncoder());

        BookServiceImpl bookService = new BookServiceImpl(bookTransformer, bookDao, authorService,
                new UserReadingInfoServiceImpl(userReadingInfoDao, userReadingInfoTransformer, userService,
                        new UserBookCategoryServiceImpl(null, null, null, null)));

        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        BookCategoryServiceImpl bookCategoryService = new BookCategoryServiceImpl(bookCategoryDao,
                new BookCategoryTransformer(new BookCategoryMapperImpl()));

        UserBookRateTransformer userBookRateTransformer = new UserBookRateTransformer(new UserBookRateMapperImpl());
        UserBookRateDaoImpl userBookRateDao = new UserBookRateDaoImpl(mock(UserBookRatingRepository.class));
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        UserServiceImpl userService1 = new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());

        BookTransformer bookTransformer1 = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao1 = new BookDaoImpl(mock(BookRepository.class));
        AuthorServiceImpl authorService1 = new AuthorServiceImpl(null, null);

        new BookController(bookService, bookCategoryService,
                new UserBookRateServiceImpl(userBookRateTransformer, userBookRateDao, userService1, new BookServiceImpl(
                        bookTransformer1, bookDao1, authorService1, new UserReadingInfoServiceImpl(null, null, null, null))));
        log.info("BookControllerTest: testCreateBook() ended");
    }

    @Test
    void testCreateBooks() {
        log.info("BookControllerTest: testCreateBooks() called");
        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao = new BookDaoImpl(mock(BookRepository.class));
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorService = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));

        UserReadingInfoDaoImpl userReadingInfoDao = new UserReadingInfoDaoImpl(mock(UserReadingInfoRepository.class));
        UserReadingInfoTransformer userReadingInfoTransformer = new UserReadingInfoTransformer(
                new UserReadingInfoMapperImpl());
        UserServiceImpl userService = new UserServiceImpl(null, null, null, new Argon2PasswordEncoder());

        BookServiceImpl bookService = new BookServiceImpl(bookTransformer, bookDao, authorService,
                new UserReadingInfoServiceImpl(userReadingInfoDao, userReadingInfoTransformer, userService,
                        new UserBookCategoryServiceImpl(null, null, null, null)));

        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        BookCategoryServiceImpl bookCategoryService = new BookCategoryServiceImpl(bookCategoryDao,
                new BookCategoryTransformer(new BookCategoryMapperImpl()));

        UserBookRateTransformer userBookRateTransformer = new UserBookRateTransformer(new UserBookRateMapperImpl());
        UserBookRateDaoImpl userBookRateDao = new UserBookRateDaoImpl(mock(UserBookRatingRepository.class));
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        UserServiceImpl userService1 = new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());

        BookTransformer bookTransformer1 = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao1 = new BookDaoImpl(mock(BookRepository.class));
        AuthorServiceImpl authorService1 = new AuthorServiceImpl(null, null);

        BookController bookController = new BookController(bookService, bookCategoryService,
                new UserBookRateServiceImpl(userBookRateTransformer, userBookRateDao, userService1, new BookServiceImpl(
                        bookTransformer1, bookDao1, authorService1, new UserReadingInfoServiceImpl(null, null, null, null))));
        ArrayList<BookDto> bookDtoList = new ArrayList<>();
        ApiResponse actualCreateBooksResult = bookController.createBooks(bookDtoList);
        assertEquals(bookDtoList, actualCreateBooksResult.getBody());
        assertTrue(actualCreateBooksResult.getSuccess());
        assertEquals("Books created successfully.", actualCreateBooksResult.getMessage());
        log.info("BookControllerTest: testCreateBooks() ended");
    }

    @Test
    void testRateBook() {
        log.info("BookControllerTest: testRateBook() called");
        UserBookRateServiceImpl userBookRateServiceImpl = mock(UserBookRateServiceImpl.class);
        UserBookRateDto userBookRateDto = new UserBookRateDto();
        when(userBookRateServiceImpl.rateBook(any())).thenReturn(userBookRateDto);
        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao = new BookDaoImpl(mock(BookRepository.class));
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorService = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));

        UserReadingInfoDaoImpl userReadingInfoDao = new UserReadingInfoDaoImpl(mock(UserReadingInfoRepository.class));
        UserReadingInfoTransformer userReadingInfoTransformer = new UserReadingInfoTransformer(
                new UserReadingInfoMapperImpl());
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        UserServiceImpl userService = new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());

        UserBookCategoryDaoImpl userBookCategoryDao = new UserBookCategoryDaoImpl(mock(UserBookCategoryRepository.class));
        UserBookCategoryTransformer userBookCategoryTransformer = new UserBookCategoryTransformer(
                new UserBookCategoryMapperImpl());
        UserServiceImpl userService1 = new UserServiceImpl(null, null, null, new Argon2PasswordEncoder());

        BookServiceImpl bookService = new BookServiceImpl(bookTransformer, bookDao, authorService,
                new UserReadingInfoServiceImpl(userReadingInfoDao, userReadingInfoTransformer, userService,
                        new UserBookCategoryServiceImpl(userBookCategoryDao, userBookCategoryTransformer, userService1,
                                new BookCategoryServiceImpl(mock(BookCategoryDao.class), null))));

        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        BookController bookController = new BookController(bookService,
                new BookCategoryServiceImpl(bookCategoryDao, new BookCategoryTransformer(new BookCategoryMapperImpl())),
                userBookRateServiceImpl);
        ApiResponse actualRateBookResult = bookController.rateBook(new UserBookRateDto());
        assertSame(userBookRateDto, actualRateBookResult.getBody());
        assertTrue(actualRateBookResult.getSuccess());
        assertEquals("Book rated successfully.", actualRateBookResult.getMessage());
        verify(userBookRateServiceImpl).rateBook(any());
        log.info("BookControllerTest: testRateBook() ended");
    }

    @Test
    void testUpdateBook() {
        log.info("BookControllerTest: testUpdateBook() called");
        Author author = new Author();
        author.setAge(1);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        author.setBirthdate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        author.setCountry("GB");
        author.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        author.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        author.setDeathdate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        author.setDescription("The characteristics of someone or something");
        author.setGender(UserGender.MALE);
        author.setId(123L);
        author.setImageUrl("https://example.org/example");
        author.setMarkedAsDeleted(true);
        author.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        author.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        author.setName("Name");

        BookCategory bookCategory = new BookCategory();
        bookCategory.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        bookCategory.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        bookCategory.setDescription("The characteristics of someone or something");
        bookCategory.setId(123L);
        bookCategory.setMarkedAsDeleted(true);
        bookCategory.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        bookCategory.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        bookCategory.setName("Name");

        Book book = new Book();
        book.setAuthor(author);
        book.setCategory(bookCategory);
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setMarkedAsDeleted(true);
        book.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        book.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setName("Name");
        book.setPagesNumber(10);
        book.setPrice(10.0d);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        book.setPublishDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        book.setRate(10.0d);
        book.setReadingDuration(1);
        book.setUsersRateCount(3L);
        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.findById(any())).thenReturn(Optional.of(book));
        new BookDaoImpl(bookRepository);
        BookService bookService = mock(BookService.class);
        BookDto bookDto = new BookDto();
        when(bookService.update(any(), any())).thenReturn(bookDto);
        BookCategoryDao bookCategoryDao = mock(BookCategoryDao.class);
        BookCategoryServiceImpl bookCategoryService = new BookCategoryServiceImpl(bookCategoryDao,
                new BookCategoryTransformer(new BookCategoryMapperImpl()));

        UserBookRateTransformer userBookRateTransformer = new UserBookRateTransformer(new UserBookRateMapperImpl());
        UserBookRateDaoImpl userBookRateDao = new UserBookRateDaoImpl(mock(UserBookRatingRepository.class));
        UserTransformer userTransformer = new UserTransformer(new UserMapperImpl());
        UserDaoImpl userDao = new UserDaoImpl(mock(UserRepository.class));
        JWTAuthenticationManagerImpl jwtAuthenticationManager = new JWTAuthenticationManagerImpl(
                new JWTAuthenticationUtil(), null, null);

        UserServiceImpl userService = new UserServiceImpl(userTransformer, userDao, jwtAuthenticationManager,
                new Argon2PasswordEncoder());

        BookTransformer bookTransformer = new BookTransformer(new BookMapperImpl());
        BookDaoImpl bookDao = new BookDaoImpl(mock(BookRepository.class));
        AuthorServiceImpl authorService = new AuthorServiceImpl(null, null);

        BookController bookController = new BookController(bookService, bookCategoryService,
                new UserBookRateServiceImpl(userBookRateTransformer, userBookRateDao, userService, new BookServiceImpl(
                        bookTransformer, bookDao, authorService, new UserReadingInfoServiceImpl(null, null, null, null))));
        ApiResponse actualUpdateBookResult = bookController.updateBook(new BookDto());
        assertSame(bookDto, actualUpdateBookResult.getBody());
        assertTrue(actualUpdateBookResult.getSuccess());
        assertEquals("Book updated successfully.", actualUpdateBookResult.getMessage());
        verify(bookService).update(any(), any());
        log.info("BookControllerTest: ended() called");
    }
}

