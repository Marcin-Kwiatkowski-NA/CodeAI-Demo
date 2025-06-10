package com.bestpractice.api.domain.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestInfoComponentGeneratedAiTests {

    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    public void setUp() {
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    public void testSetAndGetUserId() {
        // GIVEN
        String userId = "12345";

        // WHEN
        requestInfoComponent.setUserId(userId);

        // THEN
        assertEquals(userId, requestInfoComponent.getUserId());
    }

    @Test
    public void testSetAndGetUserEmail() {
        // GIVEN
        String userEmail = "user@example.com";

        // WHEN
        requestInfoComponent.setUserEmail(userEmail);

        // THEN
        assertEquals(userEmail, requestInfoComponent.getUserEmail());
    }

    @Test
    public void testSetAndGetRefreshToken() {
        // GIVEN
        boolean isRefreshToken = true;

        // WHEN
        requestInfoComponent.setRefreshToken(isRefreshToken);

        // THEN
        assertTrue(requestInfoComponent.isRefreshToken());
    }

    @Test
    public void testSetAndGetPath() {
        // GIVEN
        String path = "/api/resource";

        // WHEN
        requestInfoComponent.setPath(path);

        // THEN
        assertEquals(path, requestInfoComponent.getPath());
    }

    @Test
    public void testSetAndGetHttpMethod() {
        // GIVEN
        String httpMethod = "GET";

        // WHEN
        requestInfoComponent.setHttpMethod(httpMethod);

        // THEN
        assertEquals(httpMethod, requestInfoComponent.getHttpMethod());
    }

    @Test
    public void testSetAndGetRequestId() {
        // GIVEN
        String requestId = "req123";

        // WHEN
        requestInfoComponent.setRequestId(requestId);

        // THEN
        assertEquals(requestId, requestInfoComponent.getRequestId());
    }
}
