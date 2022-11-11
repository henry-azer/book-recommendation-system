package com.henry.bookrecommendationsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.bookrecommendationsystem.dto.base.request.AuthRequest;
import com.henry.bookrecommendationsystem.dto.base.request.RefreshTokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Henry Azer
 * @since 11/11/2022
 */
@Slf4j
class AuthenticationControllerTest {

    @Test
    void testCurrentLoggedUser() throws Exception {
        log.info("AuthenticationControllerTest: testCurrentLoggedUser() called");
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/auth/current", uriVariables);
        Object[] controllers = new Object[]{};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();
        buildResult.perform(requestBuilder);
        log.info("AuthenticationControllerTest: testCurrentLoggedUser() ended");
    }

    @Test
    void testLogin() throws Exception {
        log.info("AuthenticationControllerTest: testLogin() called");
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("user");
        authRequest.setPassword("pass");
        String content = (new ObjectMapper()).writeValueAsString(authRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/log-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup().build().perform(requestBuilder);
        log.info("AuthenticationControllerTest: testLogin() ended");
    }

    @Test
    void testLogout() throws Exception {
        log.info("AuthenticationControllerTest: testLogout() called");
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/auth/log-out", uriVariables);
        Object[] controllers = new Object[]{};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();
        buildResult.perform(requestBuilder);
        log.info("AuthenticationControllerTest: testLogout() ended");
    }

    @Test
    void testRefreshToken() throws Exception {
        log.info("AuthenticationControllerTest: testRefreshToken() called");
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/api/auth/refresh-token", uriVariables)
                .contentType(MediaType.APPLICATION_JSON);

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setEmail("jane.doe@example.org");
        refreshTokenRequest.setRefreshToken("ABC123");

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(refreshTokenRequest));
        Object[] controllers = new Object[]{};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();
        buildResult.perform(requestBuilder);
        log.info("AuthenticationControllerTest: testRefreshToken() ended");
    }
}

