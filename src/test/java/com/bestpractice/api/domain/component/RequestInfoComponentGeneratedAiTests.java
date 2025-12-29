package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class RequestInfoComponentGeneratedAiTests {

    @Mock
    private HttpServletRequest httpServletRequest;

    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        requestInfoComponent = new RequestInfoComponent();
        RequestContextHolder.setRequestAttributes(null);
    }

    @Test
    void shouldSetAndGetUserIdSuccessfully() {
        // GIVEN
        String userId = "user123";
        requestInfoComponent.setUserId(userId);

        // WHEN
        String actualUserId = requestInfoComponent.getUserId();

        // THEN
        assertEquals(userId, actualUserId);
    }

    @Test
    void shouldSetAndGetUserEmailSuccessfully() {
        // GIVEN
        String userEmail = "user@example.com";
        requestInfoComponent.setUserEmail(userEmail);

        // WHEN
        String actualUserEmail = requestInfoComponent.getUserEmail();

        // THEN
        assertEquals(userEmail, actualUserEmail);
    }

    @Test
    void shouldSetAndGetHttpMethodSuccessfully() {
        // GIVEN
        String httpMethod = "GET";
        requestInfoComponent.setHttpMethod(httpMethod);

        // WHEN
        String actualHttpMethod = requestInfoComponent.getHttpMethod();

        // THEN
        assertEquals(httpMethod, actualHttpMethod);
    }

    @Test
    void shouldSetAndGetPathSuccessfully() {
        // GIVEN
        String path = "/api/v1/users";
        requestInfoComponent.setPath(path);

        // WHEN
        String actualPath = requestInfoComponent.getPath();

        // THEN
        assertEquals(path, actualPath);
    }

    @Test
    void shouldSetAndGetRequestIdSuccessfully() {
        // GIVEN
        String requestId = "req-abc123";
        requestInfoComponent.setRequestId(requestId);

        // WHEN
        String actualRequestId = requestInfoComponent.getRequestId();

        // THEN
        assertEquals(requestId, actualRequestId);
    }

    @Test
    void shouldSetAndGetRefreshTokenFlagSuccessfully() {
        // GIVEN
        boolean refreshToken = true;
        requestInfoComponent.setRefreshToken(refreshToken);

        // WHEN
        boolean actualRefreshToken = requestInfoComponent.isRefreshToken();

        // THEN
        assertTrue(actualRefreshToken);
    }

    @Test
    void shouldReturnNullForAllFieldsWhenNotSet() {
        // GIVEN
        // No initial values set

        // WHEN
        String userId = requestInfoComponent.getUserId();
        String userEmail = requestInfoComponent.getUserEmail();
        String httpMethod = requestInfoComponent.getHttpMethod();
        String path = requestInfoComponent.getPath();
        String requestId = requestInfoComponent.getRequestId();
        boolean isRefreshToken = requestInfoComponent.isRefreshToken();

        // THEN
        assertNull(userId);
        assertNull(userEmail);
        assertNull(httpMethod);
        assertNull(path);
        assertNull(requestId);
        assertFalse(isRefreshToken);
    }

    @Test
    void shouldHandleEmptyStringValuesForPathAndHttpMethod() {
        // GIVEN
        requestInfoComponent.setPath("");
        requestInfoComponent.setHttpMethod("");

        // WHEN
        String path = requestInfoComponent.getPath();
        String httpMethod = requestInfoComponent.getHttpMethod();

        // THEN
        assertEquals("", path);
        assertEquals("", httpMethod);
    }

    @Test
    void shouldNotAllowNullValuesForUserIdAndUserEmail() {
        // GIVEN
        requestInfoComponent.setUserId(null);
        requestInfoComponent.setUserEmail(null);

        // WHEN
        String userId = requestInfoComponent.getUserId();
        String userEmail = requestInfoComponent.getUserEmail();

        // THEN
        assertNull(userId);
        assertNull(userEmail);
    }
}
