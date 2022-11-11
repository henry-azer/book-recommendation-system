package com.henry.bookrecommendationsystem.controller;

import com.henry.bookrecommendationsystem.dao.AuthorDao;
import com.henry.bookrecommendationsystem.dao.AuthorDaoImpl;
import com.henry.bookrecommendationsystem.dto.AuthorDto;
import com.henry.bookrecommendationsystem.dto.AuthorFilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import com.henry.bookrecommendationsystem.dto.base.pagination.FilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.response.ApiResponse;
import com.henry.bookrecommendationsystem.dto.base.response.PaginationResponse;
import com.henry.bookrecommendationsystem.entity.Author;
import com.henry.bookrecommendationsystem.enums.UserGender;
import com.henry.bookrecommendationsystem.repository.AuthorRepository;
import com.henry.bookrecommendationsystem.service.AuthorServiceImpl;
import com.henry.bookrecommendationsystem.transformer.AuthorTransformer;
import com.henry.bookrecommendationsystem.transformer.mapper.AuthorMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Henry Azer
 * @since 11/11/2022
 */
@Slf4j
class AuthorControllerTest {

    @Test
    void testGetService() {
        log.info("AuthorControllerTest: testGetService() called");
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorServiceImpl authorServiceImpl = new AuthorServiceImpl(authorTransformer,
                new AuthorDaoImpl(mock(AuthorRepository.class)));
        assertSame(authorServiceImpl, (new AuthorController(authorServiceImpl)).getService());
        log.info("AuthorControllerTest: testGetService() ended");
    }

    @Test
    void testFindAuthorByAuthorId() {
        log.info("AuthorControllerTest: testFindAuthorByAuthorId() called");
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
        AuthorRepository authorRepository = mock(AuthorRepository.class);
        when(authorRepository.findById((Long) any())).thenReturn(Optional.of(author));
        AuthorDaoImpl authorDao = new AuthorDaoImpl(authorRepository);
        ApiResponse actualFindAuthorByAuthorIdResult = (new AuthorController(
                new AuthorServiceImpl(new AuthorTransformer(new AuthorMapperImpl()), authorDao))).findAuthorByAuthorId(123L);
        Object body = actualFindAuthorByAuthorIdResult.getBody();
        assertTrue(body instanceof AuthorDto);
        assertTrue(actualFindAuthorByAuthorIdResult.getSuccess());
        assertEquals("Author fetched successfully.", actualFindAuthorByAuthorIdResult.getMessage());
        assertTrue(((AuthorDto) body).getMarkedAsDeleted());
        assertEquals("https://example.org/example", ((AuthorDto) body).getImageUrl());
        assertEquals("01:01", ((AuthorDto) body).getLastModificationDate().toLocalTime().toString());
        assertEquals(123L, ((AuthorDto) body).getId().longValue());
        assertEquals(UserGender.MALE, ((AuthorDto) body).getGender());
        assertEquals("The characteristics of someone or something", ((AuthorDto) body).getDescription());
        assertSame(fromResult1, ((AuthorDto) body).getDeathdate());
        assertEquals("GB", ((AuthorDto) body).getCountry());
        assertEquals("01:01", ((AuthorDto) body).getCreationDate().toLocalTime().toString());
        assertSame(fromResult, ((AuthorDto) body).getBirthdate());
        assertEquals(1, ((AuthorDto) body).getAge().intValue());
        assertEquals("Name", ((AuthorDto) body).getName());
        verify(authorRepository).findById(any());
        log.info("AuthorControllerTest: testFindAuthorByAuthorId() ended");
    }

    @Test
    void testFindAllAuthorsPaginatedAndFiltered() {
        log.info("AuthorControllerTest: testFindAllAuthorsPaginatedAndFiltered() called");
        AuthorDao authorDao = mock(AuthorDao.class);
        ArrayList<Author> authorList = new ArrayList<>();
        when(authorDao.findAllAuthorsPaginatedAndFiltered(any()))
                .thenReturn(new PageImpl<>(authorList));
        AuthorController authorController = new AuthorController(
                new AuthorServiceImpl(new AuthorTransformer(new AuthorMapperImpl()), authorDao));
        ApiResponse actualFindAllAuthorsPaginatedAndFilteredResult = authorController
                .findAllAuthorsPaginatedAndFiltered(new FilterPaginationRequest<>());
        Object body = actualFindAllAuthorsPaginatedAndFilteredResult.getBody();
        assertTrue(body instanceof PaginationResponse);
        assertTrue(actualFindAllAuthorsPaginatedAndFilteredResult.getSuccess());
        assertEquals("Authors paginated filtered fetched successfully.",
                actualFindAllAuthorsPaginatedAndFilteredResult.getMessage());
        assertTrue(((PaginationResponse<BaseDto>) body).isFirst());
        assertEquals(1, ((PaginationResponse<BaseDto>) body).getTotalNumberOfPages());
        assertEquals(0L, ((PaginationResponse<BaseDto>) body).getTotalNumberOfElements());
        assertEquals(authorList, ((PaginationResponse<BaseDto>) body).getResult());
        assertEquals(0, ((PaginationResponse<BaseDto>) body).getPageSize());
        assertEquals(1, ((PaginationResponse<BaseDto>) body).getPageNumber());
        assertTrue(((PaginationResponse<BaseDto>) body).isLast());
        verify(authorDao).findAllAuthorsPaginatedAndFiltered(any());
        log.info("AuthorControllerTest: testFindAllAuthorsPaginatedAndFiltered() ended");
    }

    @Test
    void testCreateAuthor() {
        log.info("AuthorControllerTest: testCreateAuthor() called");
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
        AuthorRepository authorRepository = mock(AuthorRepository.class);
        when(authorRepository.save((Author) any())).thenReturn(author);
        AuthorDaoImpl authorDao = new AuthorDaoImpl(authorRepository);
        AuthorController authorController = new AuthorController(
                new AuthorServiceImpl(new AuthorTransformer(new AuthorMapperImpl()), authorDao));
        ApiResponse actualCreateAuthorResult = authorController.createAuthor(new AuthorDto());
        Object body = actualCreateAuthorResult.getBody();
        assertTrue(body instanceof AuthorDto);
        assertTrue(actualCreateAuthorResult.getSuccess());
        assertEquals("Author created successfully.", actualCreateAuthorResult.getMessage());
        assertTrue(((AuthorDto) body).getMarkedAsDeleted());
        assertEquals("https://example.org/example", ((AuthorDto) body).getImageUrl());
        assertEquals("01:01", ((AuthorDto) body).getLastModificationDate().toLocalTime().toString());
        assertEquals(123L, ((AuthorDto) body).getId().longValue());
        assertEquals(UserGender.MALE, ((AuthorDto) body).getGender());
        assertEquals("The characteristics of someone or something", ((AuthorDto) body).getDescription());
        assertSame(fromResult1, ((AuthorDto) body).getDeathdate());
        assertEquals("GB", ((AuthorDto) body).getCountry());
        assertEquals("01:01", ((AuthorDto) body).getCreationDate().toLocalTime().toString());
        assertSame(fromResult, ((AuthorDto) body).getBirthdate());
        assertEquals(1, ((AuthorDto) body).getAge().intValue());
        assertEquals("Name", ((AuthorDto) body).getName());
        verify(authorRepository).save(any());
        log.info("AuthorControllerTest: testCreateAuthor() ended");
    }

    @Test
    void testCreateAuthors() {
        log.info("AuthorControllerTest: testCreateAuthors() called");
        AuthorTransformer authorTransformer = new AuthorTransformer(new AuthorMapperImpl());
        AuthorController authorController = new AuthorController(
                new AuthorServiceImpl(authorTransformer, new AuthorDaoImpl(mock(AuthorRepository.class))));
        ArrayList<AuthorDto> authorDtoList = new ArrayList<>();
        ApiResponse actualCreateAuthorsResult = authorController.createAuthors(authorDtoList);
        assertEquals(authorDtoList, actualCreateAuthorsResult.getBody());
        assertTrue(actualCreateAuthorsResult.getSuccess());
        assertEquals("Authors created successfully.", actualCreateAuthorsResult.getMessage());
        log.info("AuthorControllerTest: testCreateAuthors() ended");
    }

    @Test
    void testUpdateAuthor() {
        log.info("AuthorControllerTest: testUpdateAuthor() called");
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
        Optional<Author> ofResult = Optional.of(author);

        Author author1 = new Author();
        author1.setAge(1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        author1.setBirthdate(fromResult);
        author1.setCountry("GB");
        author1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        author1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        author1.setDeathdate(fromResult1);
        author1.setDescription("The characteristics of someone or something");
        author1.setGender(UserGender.MALE);
        author1.setId(123L);
        author1.setImageUrl("https://example.org/example");
        author1.setMarkedAsDeleted(true);
        author1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        author1.setModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        author1.setName("Name");
        AuthorRepository authorRepository = mock(AuthorRepository.class);
        when(authorRepository.save((Author) any())).thenReturn(author1);
        when(authorRepository.findById((Long) any())).thenReturn(ofResult);
        AuthorDaoImpl authorDao = new AuthorDaoImpl(authorRepository);
        AuthorController authorController = new AuthorController(
                new AuthorServiceImpl(new AuthorTransformer(new AuthorMapperImpl()), authorDao));
        ApiResponse actualUpdateAuthorResult = authorController.updateAuthor(new AuthorDto());
        Object body = actualUpdateAuthorResult.getBody();
        assertTrue(body instanceof AuthorDto);
        assertTrue(actualUpdateAuthorResult.getSuccess());
        assertEquals("Author updated successfully.", actualUpdateAuthorResult.getMessage());
        assertTrue(((AuthorDto) body).getMarkedAsDeleted());
        assertEquals("https://example.org/example", ((AuthorDto) body).getImageUrl());
        assertEquals("01:01", ((AuthorDto) body).getLastModificationDate().toLocalTime().toString());
        assertEquals(123L, ((AuthorDto) body).getId().longValue());
        assertEquals(UserGender.MALE, ((AuthorDto) body).getGender());
        assertEquals("The characteristics of someone or something", ((AuthorDto) body).getDescription());
        assertSame(fromResult1, ((AuthorDto) body).getDeathdate());
        assertEquals("GB", ((AuthorDto) body).getCountry());
        assertEquals("01:01", ((AuthorDto) body).getCreationDate().toLocalTime().toString());
        assertSame(fromResult, ((AuthorDto) body).getBirthdate());
        assertEquals(1, ((AuthorDto) body).getAge().intValue());
        assertEquals("Name", ((AuthorDto) body).getName());
        verify(authorRepository).save(any());
        verify(authorRepository).findById(any());
        log.info("AuthorControllerTest: testUpdateAuthor() ended");
    }
}

