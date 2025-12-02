package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

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
        boolean isRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(isRefreshToken);

        // THEN
        assertEquals(true, requestInfoComponent.isRefreshToken());
    }

    @Test
    void givenPath_whenSetPath_thenPathShouldBeUpdated() {
        // GIVEN
        String path = "/api/test";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("/api/test", requestInfoComponent.getPath());
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
    void givenNullUserEmail_whenSetUserEmail_thenUserEmailShouldBeNull() {
        // GIVEN
        String userEmail = null;

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals(null, requestInfoComponent.getUserEmail());
    }

    @Test
    void givenNullPath_whenSetPath_thenPathShouldBeNull() {
        // GIVEN
        String path = null;

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals(null, requestInfoComponent.getPath());
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
    void givenNullRequestId_whenSetRequestId_thenRequestIdShouldBeNull() {
        // GIVEN
        String requestId = null;

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals(null, requestInfoComponent.getRequestId());
    }
}
