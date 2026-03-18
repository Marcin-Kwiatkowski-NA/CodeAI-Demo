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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RequestInfoComponentGeneratedAiTests {

    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    void testSetAndGetUserId() {
        // GIVEN
        String expectedUserId = "user123";

        // WHEN
        requestInfoComponent.setUserId(expectedUserId);
        String actualUserId = requestInfoComponent.getUserId();

        // THEN
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void testSetAndGetUserEmail() {
        // GIVEN
        String expectedEmail = "user@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(expectedEmail);
        String actualEmail = requestInfoComponent.getUserEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testSetAndIsRefreshToken() {
        // GIVEN
        boolean expectedRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedRefreshToken);
        boolean actualRefreshToken = requestInfoComponent.isRefreshToken();

        // THEN
        assertEquals(expectedRefreshToken, actualRefreshToken);
    }

    @Test
    void testSetAndGetPath() {
        // GIVEN
        String expectedPath = "/api/test";

        // WHEN
        requestInfoComponent.setPath(expectedPath);
        String actualPath = requestInfoComponent.getPath();

        // THEN
        assertEquals(expectedPath, actualPath);
    }

    @Test
    void testSetAndGetHttpMethod() {
        // GIVEN
        String expectedMethod = "POST";

        // WHEN
        requestInfoComponent.setHttpMethod(expectedMethod);
        String actualMethod = requestInfoComponent.getHttpMethod();

        // THEN
        assertEquals(expectedMethod, actualMethod);
    }

    @Test
    void testSetAndGetRequestId() {
        // GIVEN
        String expectedRequestId = "req-001";

        // WHEN
        requestInfoComponent.setRequestId(expectedRequestId);
        String actualRequestId = requestInfoComponent.getRequestId();

        // THEN
        assertEquals(expectedRequestId, actualRequestId);
    }

    @Test
    void testSetNullValuesDoesNotThrowException() {
        // GIVEN
        String nullValue = null;

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            requestInfoComponent.setUserId(nullValue);
            requestInfoComponent.setUserEmail(nullValue);
            requestInfoComponent.setPath(nullValue);
            requestInfoComponent.setHttpMethod(nullValue);
            requestInfoComponent.setRequestId(nullValue);
        });

        // THEN
        assertEquals(nullValue, requestInfoComponent.getUserId());
        assertEquals(nullValue, requestInfoComponent.getUserEmail());
        assertEquals(nullValue, requestInfoComponent.getPath());
        assertEquals(nullValue, requestInfoComponent.getHttpMethod());
        assertEquals(nullValue, requestInfoComponent.getRequestId());
    }

    @Test
    void testNoExceptionThrownWhenSettingValidValues() {
        // GIVEN
        String userId = "safeUser";
        String email = "safe@example.com";
        String path = "/api/safe";
        String method = "GET";
        String requestId = "req-safe";

        // WHEN & THEN
        assertDoesNotThrow(() -> {
            requestInfoComponent.setUserId(userId);
            requestInfoComponent.setUserEmail(email);
            requestInfoComponent.setPath(path);
            requestInfoComponent.setHttpMethod(method);
            requestInfoComponent.setRequestId(requestId);
        });

        // THEN
        assertEquals(userId, requestInfoComponent.getUserId());
        assertEquals(email, requestInfoComponent.getUserEmail());
        assertEquals(path, requestInfoComponent.getPath());
        assertEquals(method, requestInfoComponent.getHttpMethod());
        assertEquals(requestId, requestInfoComponent.getRequestId());
    }
}
