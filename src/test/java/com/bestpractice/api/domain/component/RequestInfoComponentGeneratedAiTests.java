package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void testSetAndGetUserId() {
        // GIVEN
        String userId = "user123";

        // WHEN
        requestInfoComponent.setUserId(userId);
        String result = requestInfoComponent.getUserId();

        // THEN
        assertEquals(userId, result);
    }

    @Test
    void testSetAndGetUserEmail() {
        // GIVEN
        String userEmail = "user@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);
        String result = requestInfoComponent.getUserEmail();

        // THEN
        assertEquals(userEmail, result);
    }

    @Test
    void testSetAndGetRefreshTokenTrue() {
        // GIVEN
        boolean refreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(refreshToken);
        boolean result = requestInfoComponent.isRefreshToken();

        // THEN
        assertEquals(true, result);
    }

    @Test
    void testSetAndGetRefreshTokenFalse() {
        // GIVEN
        boolean refreshToken = false;

        // WHEN
        requestInfoComponent.setRefreshToken(refreshToken);
        boolean result = requestInfoComponent.isRefreshToken();

        // THEN
        assertEquals(false, result);
    }

    @Test
    void testSetAndGetPath() {
        // GIVEN
        String path = "/api/resource";

        // WHEN
        requestInfoComponent.setPath(path);
        String result = requestInfoComponent.getPath();

        // THEN
        assertEquals(path, result);
    }

    @Test
    void testSetAndGetHttpMethod() {
        // GIVEN
        String httpMethod = "POST";

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);
        String result = requestInfoComponent.getHttpMethod();

        // THEN
        assertEquals(httpMethod, result);
    }

    @Test
    void testSetAndGetRequestId() {
        // GIVEN
        String requestId = "req-456";

        // WHEN
        requestInfoComponent.setRequestId(requestId);
        String result = requestInfoComponent.getRequestId();

        // THEN
        assertEquals(requestId, result);
    }

    @Test
    void testDefaultValues() {
        // GIVEN
        // component is freshly instantiated in @BeforeEach

        // WHEN
        String userId = requestInfoComponent.getUserId();
        String userEmail = requestInfoComponent.getUserEmail();
        boolean isRefreshToken = requestInfoComponent.isRefreshToken();
        String path = requestInfoComponent.getPath();
        String httpMethod = requestInfoComponent.getHttpMethod();
        String requestId = requestInfoComponent.getRequestId();

        // THEN
        assertEquals(null, userId);
        assertEquals(null, userEmail);
        assertEquals(false, isRefreshToken);
        assertEquals(null, path);
        assertEquals(null, httpMethod);
        assertEquals(null, requestId);
    }

    @Test
    void testSettingNullValues() {
        // GIVEN
        // no initial values

        // WHEN
        requestInfoComponent.setUserId(null);
        requestInfoComponent.setUserEmail(null);
        requestInfoComponent.setPath(null);
        requestInfoComponent.setHttpMethod(null);
        requestInfoComponent.setRequestId(null);

        // THEN
        assertEquals(null, requestInfoComponent.getUserId());
        assertEquals(null, requestInfoComponent.getUserEmail());
        assertEquals(null, requestInfoComponent.getPath());
        assertEquals(null, requestInfoComponent.getHttpMethod());
        assertEquals(null, requestInfoComponent.getRequestId());
    }

    @Test
    void testMultiplePropertyAssignments() {
        // GIVEN
        String userId = "user123";
        String userEmail = "user@example.com";
        String path = "/api/resource";
        String httpMethod = "PUT";
        String requestId = "req-789";

        // WHEN
        requestInfoComponent.setUserId(userId);
        requestInfoComponent.setUserEmail(userEmail);
        requestInfoComponent.setPath(path);
        requestInfoComponent.setHttpMethod(httpMethod);
        requestInfoComponent.setRequestId(requestId);
        requestInfoComponent.setRefreshToken(true);

        // THEN
        assertEquals(userId, requestInfoComponent.getUserId());
        assertEquals(userEmail, requestInfoComponent.getUserEmail());
        assertEquals(path, requestInfoComponent.getPath());
        assertEquals(httpMethod, requestInfoComponent.getHttpMethod());
        assertEquals(requestId, requestInfoComponent.getRequestId());
        assertEquals(true, requestInfoComponent.isRefreshToken());
    }
}
