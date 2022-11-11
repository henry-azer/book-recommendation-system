package com.henry.bookrecommendationsystem.controller;

import com.henry.bookrecommendationsystem.dto.UserBookCategoryDto;
import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.dto.UserReadingInfoDto;
import com.henry.bookrecommendationsystem.enums.UserGender;
import com.henry.bookrecommendationsystem.enums.UserMartialStatus;
import com.henry.bookrecommendationsystem.enums.UserReadingLevel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Henry Azer
 * @since 11/11/2022
 */
@Slf4j
class UserControllerTest {

    @Test
    void testCheckIsEmailAlreadyExists() throws Exception {
        log.info("UserControllerTest: testCheckIsEmailAlreadyExists() called");
        Object[] uriVariables = new Object[]{"jane.doe@example.org"};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/find-is-email-exists/{email}",
                uriVariables);
        Object[] controllers = new Object[]{};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();
        buildResult.perform(requestBuilder);
        log.info("UserControllerTest: testCheckIsEmailAlreadyExists() ended");
    }

    @Test
    void testCreateUser() {
        log.info("UserControllerTest: testCreateUser() called");
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/user", uriVariables)
                .contentType(MediaType.APPLICATION_JSON);

        UserDto userDto = new UserDto();
        userDto.setAge(1);
        ZoneId zone = ZoneId.of("UTC");
        userDto.setBirthdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(zone).toInstant()));
        userDto.setCountry("GB");
        userDto.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setEmail("jane.doe@example.org");
        userDto.setFirstName("Jane");
        userDto.setGender(UserGender.MALE);
        userDto.setId(123L);
        userDto.setImageUrl("https://example.org/example");
        userDto.setLastModificationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userDto.setLastName("Doe");
        userDto.setMaritalStatus(UserMartialStatus.SINGLE);
        userDto.setMarkedAsDeleted(true);
        userDto.setPassword("iloveyou");
        userDto.setPhoneNumber("4105551212");
        Object[] controllers = new Object[]{};
        MockMvcBuilders.standaloneSetup(controllers).build();
        log.info("UserControllerTest: testCreateUser() ended");

    }

    @Test
    void testCreateUserReadingInfo() {
        log.info("UserControllerTest: testCreateUserReadingInfo() called");
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/api/user/reading-info", uriVariables)
                .contentType(MediaType.APPLICATION_JSON);

        UserReadingInfoDto userReadingInfoDto = new UserReadingInfoDto();
        userReadingInfoDto.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userReadingInfoDto.setId(123L);
        userReadingInfoDto.setLastModificationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        userReadingInfoDto.setMarkedAsDeleted(true);
        userReadingInfoDto.setReadingLevel(UserReadingLevel.BEGINNER);
        UserDto user = new UserDto();
        userReadingInfoDto.setUser(user);
        ArrayList<UserBookCategoryDto> userBookCategories = new ArrayList<>();
        userReadingInfoDto.setUserBookCategories(userBookCategories);
        Object[] controllers = new Object[]{};
        MockMvcBuilders.standaloneSetup(controllers).build();
        log.info("UserControllerTest: testCreateUserReadingInfo() ended");
    }

    @Test
    void testFindUserReadingInfo() throws Exception {
        log.info("UserControllerTest: testFindUserReadingInfo() called");
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/find-reading-info",
                uriVariables);
        Object[] controllers = new Object[]{};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();
        buildResult.perform(requestBuilder);
        log.info("UserControllerTest: testFindUserReadingInfo() ended");
    }

    @Test
    void testGetUserGenders() throws Exception {
        log.info("UserControllerTest: testGetUserGenders() ended");
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/find-all-genders",
                uriVariables);
        Object[] controllers = new Object[]{};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();
        buildResult.perform(requestBuilder);
        log.info("UserControllerTest: testGetUserGenders() ended");
    }

    @Test
    void testGetUserMartialStatuses() throws Exception {
        log.info("UserControllerTest: testGetUserMartialStatuses() called");
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/find-all-martial-statuses",
                uriVariables);
        Object[] controllers = new Object[]{};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();
        buildResult.perform(requestBuilder);
        log.info("UserControllerTest: testGetUserMartialStatuses() ended");
    }
}

