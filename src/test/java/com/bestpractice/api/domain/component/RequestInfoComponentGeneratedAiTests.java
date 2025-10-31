package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestInfoComponentGeneratedAiTests {

    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    void testSetAndGetUserId() {
        // GIVEN: a userId value
        String expectedUserId = "user123";

        // WHEN: setting the userId
        requestInfoComponent.setUserId(expectedUserId);

        // THEN: the retrieved userId should match the expected value
        assertEquals(expectedUserId, requestInfoComponent.getUserId());
    }

    @Test
    void testSetAndGetUserEmail() {
        // GIVEN: a userEmail value
        String expectedEmail = "user@example.com";

        // WHEN: setting the userEmail
        requestInfoComponent.setUserEmail(expectedEmail);

        // THEN: the retrieved userEmail should match the expected value
        assertEquals(expectedEmail, requestInfoComponent.getUserEmail());
    }

    @Test
    void testSetAndIsRefreshTokenTrue() {
        // GIVEN: a refreshToken value set to true
        boolean expectedRefreshToken = true;

        // WHEN: setting the refreshToken
        requestInfoComponent.setRefreshToken(expectedRefreshToken);

        // THEN: the retrieved refreshToken should be true
        assertTrue(requestInfoComponent.isRefreshToken());
    }

    @Test
    void testSetAndIsRefreshTokenFalse() {
        // GIVEN: a refreshToken value set to false
        boolean expectedRefreshToken = false;

        // WHEN: setting the refreshToken
        requestInfoComponent.setRefreshToken(expectedRefreshToken);

        // THEN: the retrieved refreshToken should be false
        assertFalse(requestInfoComponent.isRefreshToken());
    }

    @Test
    void testSetAndGetPath() {
        // GIVEN: a path value
        String expectedPath = "/api/test";

        // WHEN: setting the path
        requestInfoComponent.setPath(expectedPath);

        // THEN: the retrieved path should match the expected value
        assertEquals(expectedPath, requestInfoComponent.getPath());
    }

    @Test
    void testSetAndGetHttpMethod() {
        // GIVEN: an httpMethod value
        String expectedMethod = "POST";

        // WHEN: setting the httpMethod
        requestInfoComponent.setHttpMethod(expectedMethod);

        // THEN: the retrieved httpMethod should match the expected value
        assertEquals(expectedMethod, requestInfoComponent.getHttpMethod());
    }

    @Test
    void testSetAndGetRequestId() {
        // GIVEN: a requestId value
        String expectedRequestId = "req-001";

        // WHEN: setting the requestId
        requestInfoComponent.setRequestId(expectedRequestId);

        // THEN: the retrieved requestId should match the expected value
        assertEquals(expectedRequestId, requestInfoComponent.getRequestId());
    }
}
