package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestInfoComponentGeneratedAiTests {

    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        // Reset the state before each test
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    void givenUserId_whenSetUserId_thenUserIdShouldBeUpdated() {
        // GIVEN
        String userId = "testUserId";

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals("testUserId", requestInfoComponent.getUserId());
    }

    @Test
    void givenUserEmail_whenSetUserEmail_thenUserEmailShouldBeUpdated() {
        // GIVEN
        String userEmail = "test@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals("test@example.com", requestInfoComponent.getUserEmail());
    }

    @Test
    void givenRefreshTokenFlag_whenSetRefreshToken_thenRefreshTokenFlagShouldBeUpdated() {
        // GIVEN
        boolean refreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(refreshToken);

        // THEN
        assertEquals(true, requestInfoComponent.isRefreshToken());
    }

    @Test
    void givenPath_whenSetPath_thenPathShouldBeUpdated() {
        // GIVEN
        String path = "/test/path";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("/test/path", requestInfoComponent.getPath());
    }

    @Test
    void givenHttpMethod_whenSetHttpMethod_thenHttpMethodShouldBeUpdated() {
        // GIVEN
        String httpMethod = "POST";

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertEquals("POST", requestInfoComponent.getHttpMethod());
    }

    @Test
    void givenRequestId_whenSetRequestId_thenRequestIdShouldBeUpdated() {
        // GIVEN
        String requestId = "12345";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals("12345", requestInfoComponent.getRequestId());
    }
}
