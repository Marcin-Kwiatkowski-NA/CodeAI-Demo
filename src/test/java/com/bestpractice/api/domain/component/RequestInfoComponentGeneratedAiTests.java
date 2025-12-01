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
    void givenRefreshToken_whenSetRefreshToken_thenRefreshTokenShouldBeUpdated() {
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

    @Test
    void givenNullUserId_whenSetUserId_thenUserIdShouldBeNull() {
        // GIVEN
        String userId = null;

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals(null, requestInfoComponent.getUserId());
    }

    @Test
    void givenEmptyUserEmail_whenSetUserEmail_thenUserEmailShouldBeEmpty() {
        // GIVEN
        String userEmail = "";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals("", requestInfoComponent.getUserEmail());
    }

    @Test
    void givenInvalidPath_whenSetPath_thenPathShouldBeUpdated() {
        // GIVEN
        String path = "invalid_path";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("invalid_path", requestInfoComponent.getPath());
    }

    @Test
    void givenNullHttpMethod_whenSetHttpMethod_thenHttpMethodShouldBeNull() {
        // GIVEN
        String httpMethod = null;

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertEquals(null, requestInfoComponent.getHttpMethod());
    }

    @Test
    void givenEmptyRequestId_whenSetRequestId_thenRequestIdShouldBeEmpty() {
        // GIVEN
        String requestId = "";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals("", requestInfoComponent.getRequestId());
    }
}
