package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestInfoComponentGeneratedAiTests {

    @InjectMocks
    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    void givenUserId_whenSetUserId_thenUserIdIsUpdated() {
        // GIVEN
        String userId = "testUserId";

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals("testUserId", requestInfoComponent.getUserId());
    }

    @Test
    void givenUserEmail_whenSetUserEmail_thenUserEmailIsUpdated() {
        // GIVEN
        String userEmail = "test@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals("test@example.com", requestInfoComponent.getUserEmail());
    }

    @Test
    void givenRefreshToken_whenSetRefreshToken_thenRefreshTokenIsUpdated() {
        // GIVEN
        boolean isRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(isRefreshToken);

        // THEN
        assertEquals(true, requestInfoComponent.isRefreshToken());
    }

    @Test
    void givenPath_whenSetPath_thenPathIsUpdated() {
        // GIVEN
        String path = "/test/path";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("/test/path", requestInfoComponent.getPath());
    }

    @Test
    void givenHttpMethod_whenSetHttpMethod_thenHttpMethodIsUpdated() {
        // GIVEN
        String httpMethod = "POST";

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertEquals("POST", requestInfoComponent.getHttpMethod());
    }

    @Test
    void givenRequestId_whenSetRequestId_thenRequestIdIsUpdated() {
        // GIVEN
        String requestId = "12345";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals("12345", requestInfoComponent.getRequestId());
    }
}
