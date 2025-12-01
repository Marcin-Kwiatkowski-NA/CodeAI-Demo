package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(requestInfoComponent.getUserId()).isEqualTo(userId);
    }

    @Test
    void givenUserEmail_whenSetUserEmail_thenUserEmailIsUpdated() {
        // GIVEN
        String userEmail = "test@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertThat(requestInfoComponent.getUserEmail()).isEqualTo(userEmail);
    }

    @Test
    void givenRefreshTokenFlag_whenSetRefreshToken_thenRefreshTokenFlagIsUpdated() {
        // GIVEN
        boolean isRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(isRefreshToken);

        // THEN
        assertThat(requestInfoComponent.isRefreshToken()).isTrue();
    }

    @Test
    void givenPath_whenSetPath_thenPathIsUpdated() {
        // GIVEN
        String path = "/test/path";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertThat(requestInfoComponent.getPath()).isEqualTo(path);
    }

    @Test
    void givenHttpMethod_whenSetHttpMethod_thenHttpMethodIsUpdated() {
        // GIVEN
        String httpMethod = "POST";

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertThat(requestInfoComponent.getHttpMethod()).isEqualTo(httpMethod);
    }

    @Test
    void givenRequestId_whenSetRequestId_thenRequestIdIsUpdated() {
        // GIVEN
        String requestId = "12345";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertThat(requestInfoComponent.getRequestId()).isEqualTo(requestId);
    }
}
