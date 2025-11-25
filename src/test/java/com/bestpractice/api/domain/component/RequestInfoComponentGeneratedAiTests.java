package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestInfoComponentGeneratedAiTests {

    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    void givenUserId_whenSetUserId_thenUserIdIsSetCorrectly() {
        // GIVEN
        String userId = "testUserId";

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals("testUserId", requestInfoComponent.getUserId());
    }

    @Test
    void givenUserEmail_whenSetUserEmail_thenUserEmailIsSetCorrectly() {
        // GIVEN
        String userEmail = "test@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals("test@example.com", requestInfoComponent.getUserEmail());
    }

    @Test
    void givenRefreshToken_whenSetRefreshToken_thenRefreshTokenIsSetCorrectly() {
        // GIVEN
        boolean isRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(isRefreshToken);

        // THEN
        assertEquals(true, requestInfoComponent.isRefreshToken());
    }

    @Test
    void givenPath_whenSetPath_thenPathIsSetCorrectly() {
        // GIVEN
        String path = "/api/test";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("/api/test", requestInfoComponent.getPath());
    }

    @Test
    void givenHttpMethod_whenSetHttpMethod_thenHttpMethodIsSetCorrectly() {
        // GIVEN
        String httpMethod = "POST";

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertEquals("POST", requestInfoComponent.getHttpMethod());
    }

    @Test
    void givenRequestId_whenSetRequestId_thenRequestIdIsSetCorrectly() {
        // GIVEN
        String requestId = "12345";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals("12345", requestInfoComponent.getRequestId());
    }
}
