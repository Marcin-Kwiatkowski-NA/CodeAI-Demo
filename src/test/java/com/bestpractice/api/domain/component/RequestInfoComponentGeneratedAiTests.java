package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RequestInfoComponentGeneratedAiTests {

    @InjectMocks
    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    void shouldSetAndGetUserId() {
        // GIVEN
        String userId = "user123";

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals(userId, requestInfoComponent.getUserId());
    }

    @Test
    void shouldSetAndGetUserEmail() {
        // GIVEN
        String userEmail = "user@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals(userEmail, requestInfoComponent.getUserEmail());
    }

    @Test
    void shouldSetAndGetRefreshToken() {
        // GIVEN
        boolean refreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(refreshToken);

        // THEN
        assertEquals(refreshToken, requestInfoComponent.isRefreshToken());
    }

    @Test
    void shouldSetAndGetPath() {
        // GIVEN
        String path = "/api/test";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals(path, requestInfoComponent.getPath());
    }

    @Test
    void shouldSetAndGetHttpMethod() {
        // GIVEN
        String httpMethod = "GET";

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertEquals(httpMethod, requestInfoComponent.getHttpMethod());
    }

    @Test
    void shouldSetAndGetRequestId() {
        // GIVEN
        String requestId = "req123";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals(requestId, requestInfoComponent.getRequestId());
    }
}
