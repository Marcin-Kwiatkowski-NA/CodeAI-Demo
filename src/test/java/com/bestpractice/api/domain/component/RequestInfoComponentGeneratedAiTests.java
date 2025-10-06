package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
    void testSetAndIsRefreshTokenTrue() {
        // GIVEN
        boolean expectedValue = true;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedValue);

        // THEN
        assertTrue(requestInfoComponent.isRefreshToken());
    }

    @Test
    void testSetAndIsRefreshTokenFalse() {
        // GIVEN
        boolean expectedValue = false;

        // WHEN
        requestInfoComponent.setRefreshToken(expectedValue);

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
    void testSetUserIdWithNullValue() {
        // GIVEN
        String expectedUserId = null;

        // WHEN
        requestInfoComponent.setUserId(expectedUserId);

        // THEN
        assertEquals(expectedUserId, requestInfoComponent.getUserId());
    }

    @Test
    void testSetUserEmailWithNullValue() {
        // GIVEN
        String expectedEmail = null;

        // WHEN
        requestInfoComponent.setUserEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, requestInfoComponent.getUserEmail());
    }

    @Test
    void testSetPathWithNullValue() {
        // GIVEN
        String expectedPath = null;

        // WHEN
        requestInfoComponent.setPath(expectedPath);

        // THEN
        assertEquals(expectedPath, requestInfoComponent.getPath());
    }

    @Test
    void testSetHttpMethodWithNullValue() {
        // GIVEN
        String expectedMethod = null;

        // WHEN
        requestInfoComponent.setHttpMethod(expectedMethod);

        // THEN
        assertEquals(expectedMethod, requestInfoComponent.getHttpMethod());
    }

    @Test
    void testSetRequestIdWithNullValue() {
        // GIVEN
        String expectedRequestId = null;

        // WHEN
        requestInfoComponent.setRequestId(expectedRequestId);

        // THEN
        assertEquals(expectedRequestId, requestInfoComponent.getRequestId());
    }
}
