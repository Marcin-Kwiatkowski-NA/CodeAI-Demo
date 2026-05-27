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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    void testSetAndIsRefreshTokenTrue() {
        // GIVEN
        boolean expectedRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedRefreshToken);
        boolean actualRefreshToken = requestInfoComponent.isRefreshToken();

        // THEN
        assertTrue(actualRefreshToken);
    }

    @Test
    void testSetAndIsRefreshTokenFalse() {
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
    void testSetNullValues() {
        // GIVEN
        String expectedUserId = null;
        String expectedEmail = null;
        String expectedPath = null;
        String expectedHttpMethod = null;
        String expectedRequestId = null;

        // WHEN
        requestInfoComponent.setUserId(expectedUserId);
        requestInfoComponent.setUserEmail(expectedEmail);
        requestInfoComponent.setPath(expectedPath);
        requestInfoComponent.setHttpMethod(expectedHttpMethod);
        requestInfoComponent.setRequestId(expectedRequestId);

        // THEN
        assertEquals(expectedUserId, requestInfoComponent.getUserId());
        assertEquals(expectedEmail, requestInfoComponent.getUserEmail());
        assertEquals(expectedPath, requestInfoComponent.getPath());
        assertEquals(expectedHttpMethod, requestInfoComponent.getHttpMethod());
        assertEquals(expectedRequestId, requestInfoComponent.getRequestId());
    }

    @Test
    void testDefaultValuesAfterInitialization() {
        // GIVEN
        // A new instance of RequestInfoComponent is created in setUp()

        // WHEN
        String userId = requestInfoComponent.getUserId();
        String userEmail = requestInfoComponent.getUserEmail();
        boolean refreshToken = requestInfoComponent.isRefreshToken();
        String path = requestInfoComponent.getPath();
        String httpMethod = requestInfoComponent.getHttpMethod();
        String requestId = requestInfoComponent.getRequestId();

        // THEN
        assertEquals(null, userId);
        assertEquals(null, userEmail);
        assertFalse(refreshToken);
        assertEquals(null, path);
        assertEquals(null, httpMethod);
        assertEquals(null, requestId);
    }
}
