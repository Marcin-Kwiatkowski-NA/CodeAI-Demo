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
        String path = "/api/test";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("/api/test", requestInfoComponent.getPath());
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

    @Test
    void givenNullUserId_whenSetUserId_thenUserIdIsNull() {
        // GIVEN
        String userId = null;

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals(null, requestInfoComponent.getUserId());
    }

    @Test
    void givenNullUserEmail_whenSetUserEmail_thenUserEmailIsNull() {
        // GIVEN
        String userEmail = null;

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals(null, requestInfoComponent.getUserEmail());
    }

    @Test
    void givenNullPath_whenSetPath_thenPathIsNull() {
        // GIVEN
        String path = null;

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals(null, requestInfoComponent.getPath());
    }

    @Test
    void givenNullHttpMethod_whenSetHttpMethod_thenHttpMethodIsNull() {
        // GIVEN
        String httpMethod = null;

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertEquals(null, requestInfoComponent.getHttpMethod());
    }

    @Test
    void givenNullRequestId_whenSetRequestId_thenRequestIdIsNull() {
        // GIVEN
        String requestId = null;

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals(null, requestInfoComponent.getRequestId());
    }

    @Test
    void givenEmptyUserId_whenSetUserId_thenUserIdIsUpdated() {
        // GIVEN
        String userId = "";

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals("", requestInfoComponent.getUserId());
    }

    @Test
    void givenEmptyUserEmail_whenSetUserEmail_thenUserEmailIsUpdated() {
        // GIVEN
        String userEmail = "";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals("", requestInfoComponent.getUserEmail());
    }

    @Test
    void givenEmptyPath_whenSetPath_thenPathIsUpdated() {
        // GIVEN
        String path = "";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("", requestInfoComponent.getPath());
    }

    @Test
    void givenEmptyHttpMethod_whenSetHttpMethod_thenHttpMethodIsUpdated() {
        // GIVEN
        String httpMethod = "";

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertEquals("", requestInfoComponent.getHttpMethod());
    }

   @Test
    void givenEmptyRequestId_whenSetRequestId_thenRequestIdIsUpdated() {
        // GIVEN
        String requestId = "";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals("", requestInfoComponent.getRequestId());
    }
}
