package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
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

        // THEN
        assertEquals(expectedUserId, requestInfoComponent.getUserId());
    }

    @Test
    void testSetAndGetUserEmail() {
        // GIVEN
        String expectedEmail = "user@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, requestInfoComponent.getUserEmail());
    }

    @Test
    void testSetAndGetRefreshTokenTrue() {
        // GIVEN
        boolean expectedRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedRefreshToken);

        // THEN
        assertTrue(requestInfoComponent.isRefreshToken());
    }

    @Test
    void testSetAndGetRefreshTokenFalse() {
        // GIVEN
        boolean expectedRefreshToken = false;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedRefreshToken);

        // THEN
        assertFalse(requestInfoComponent.isRefreshToken());
    }

    @Test
    void testSetAndGetPath() {
        // GIVEN
        String expectedPath = "/api/test";

        // WHEN
        requestInfoComponent.setPath(expectedPath);

        // THEN
        assertEquals(expectedPath, requestInfoComponent.getPath());
    }

    @Test
    void testSetAndGetHttpMethod() {
        // GIVEN
        String expectedMethod = "POST";

        // WHEN
        requestInfoComponent.setHttpMethod(expectedMethod);

        // THEN
        assertEquals(expectedMethod, requestInfoComponent.getHttpMethod());
    }

    @Test
    void testSetAndGetRequestId() {
        // GIVEN
        String expectedRequestId = "req-001";

        // WHEN
        requestInfoComponent.setRequestId(expectedRequestId);

        // THEN
        assertEquals(expectedRequestId, requestInfoComponent.getRequestId());
    }

    @Test
    void testDefaultValuesAfterInitialization() {
        // GIVEN
        // WHEN
        // THEN
        assertNull(requestInfoComponent.getUserId());
        assertNull(requestInfoComponent.getUserEmail());
        assertFalse(requestInfoComponent.isRefreshToken());
        assertNull(requestInfoComponent.getPath());
        assertNull(requestInfoComponent.getHttpMethod());
        assertNull(requestInfoComponent.getRequestId());
    }

    @Test
    void testSettersAcceptNullValues() {
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
}
