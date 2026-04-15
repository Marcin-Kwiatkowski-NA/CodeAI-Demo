package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
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
    void testSetAndGetRefreshTokenTrue() {
        // GIVEN
        boolean expectedRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedRefreshToken);
        boolean actualRefreshToken = requestInfoComponent.isRefreshToken();

        // THEN
        assertTrue(actualRefreshToken);
    }

    @Test
    void testSetAndGetRefreshTokenFalse() {
        // GIVEN
        boolean expectedRefreshToken = false;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedRefreshToken);
        boolean actualRefreshToken = requestInfoComponent.isRefreshToken();

        // THEN
        assertFalse(actualRefreshToken);
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
    void testDefaultValuesAfterInitialization() {
        // GIVEN
        // WHEN
        String userId = requestInfoComponent.getUserId();
        String userEmail = requestInfoComponent.getUserEmail();
        boolean refreshToken = requestInfoComponent.isRefreshToken();
        String path = requestInfoComponent.getPath();
        String httpMethod = requestInfoComponent.getHttpMethod();
        String requestId = requestInfoComponent.getRequestId();

        // THEN
        assertNull(userId);
        assertNull(userEmail);
        assertFalse(refreshToken);
        assertNull(path);
        assertNull(httpMethod);
        assertNull(requestId);
    }

    @Test
    void testSetNullValues() {
        // GIVEN
        String nullValue = null;

        // WHEN
        requestInfoComponent.setUserId(nullValue);
        requestInfoComponent.setUserEmail(nullValue);
        requestInfoComponent.setPath(nullValue);
        requestInfoComponent.setHttpMethod(nullValue);
        requestInfoComponent.setRequestId(nullValue);

        // THEN
        assertNull(requestInfoComponent.getUserId());
        assertNull(requestInfoComponent.getUserEmail());
        assertNull(requestInfoComponent.getPath());
        assertNull(requestInfoComponent.getHttpMethod());
        assertNull(requestInfoComponent.getRequestId());
    }

    @Test
    void testMultipleFieldAssignments() {
        // GIVEN
        String expectedUserId = "userABC";
        String expectedEmail = "abc@example.com";
        String expectedPath = "/api/v1/resource";
        String expectedMethod = "GET";
        String expectedRequestId = "req-999";
        boolean expectedRefreshToken = true;

        // WHEN
        requestInfoComponent.setUserId(expectedUserId);
        requestInfoComponent.setUserEmail(expectedEmail);
        requestInfoComponent.setPath(expectedPath);
        requestInfoComponent.setHttpMethod(expectedMethod);
        requestInfoComponent.setRequestId(expectedRequestId);
        requestInfoComponent.setRefreshToken(expectedRefreshToken);

        // THEN
        assertEquals(expectedUserId, requestInfoComponent.getUserId());
        assertEquals(expectedEmail, requestInfoComponent.getUserEmail());
        assertEquals(expectedPath, requestInfoComponent.getPath());
        assertEquals(expectedMethod, requestInfoComponent.getHttpMethod());
        assertEquals(expectedRequestId, requestInfoComponent.getRequestId());
        assertTrue(requestInfoComponent.isRefreshToken());
    }
}
