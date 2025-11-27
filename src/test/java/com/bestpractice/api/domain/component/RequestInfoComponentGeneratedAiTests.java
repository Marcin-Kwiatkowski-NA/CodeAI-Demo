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
    void testSetAndGetUserId() {
        // GIVEN
        String userId = "testUserId";

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals("testUserId", requestInfoComponent.getUserId());
    }

    @Test
    void testSetAndGetUserEmail() {
        // GIVEN
        String userEmail = "test@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals("test@example.com", requestInfoComponent.getUserEmail());
    }

    @Test
    void testSetAndGetRefreshToken() {
        // GIVEN
        boolean isRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(isRefreshToken);

        // THEN
        assertEquals(true, requestInfoComponent.isRefreshToken());
    }

    @Test
    void testSetAndGetPath() {
        // GIVEN
        String path = "/test/path";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals("/test/path", requestInfoComponent.getPath());
    }

    @Test
    void testSetAndGetHttpMethod() {
        // GIVEN
        String httpMethod = "POST";

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertEquals("POST", requestInfoComponent.getHttpMethod());
    }

    @Test
    void testSetAndGetRequestId() {
        // GIVEN
        String requestId = "12345";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals("12345", requestInfoComponent.getRequestId());
    }

    @Test
    void testSetUserIdWithNullValue() {
        // GIVEN
        String userId = null;

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals(null, requestInfoComponent.getUserId());
    }

    @Test
    void testSetUserEmailWithInvalidFormat() {
        // GIVEN
        String invalidEmail = "invalid-email";

        // WHEN
        requestInfoComponent.setUserEmail(invalidEmail);

        // THEN
        assertEquals("invalid-email", requestInfoComponent.getUserEmail());
    }

    @Test
    void testSetHttpMethodWithUnsupportedValue() {
        // GIVEN
        String unsupportedHttpMethod = "INVALID_METHOD";

        // WHEN
        requestInfoComponent.setHttpMethod(unsupportedHttpMethod);

        // THEN
        assertEquals("INVALID_METHOD", requestInfoComponent.getHttpMethod());
    }

    @Test
    void testSetPathWithEmptyValue() {
        // GIVEN
        String emptyPath = "";

        // WHEN
        requestInfoComponent.setPath(emptyPath);

        // THEN
        assertEquals("", requestInfoComponent.getPath());
    }
}
