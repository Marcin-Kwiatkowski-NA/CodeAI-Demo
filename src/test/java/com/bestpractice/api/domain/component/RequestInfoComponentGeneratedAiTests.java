package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.InnerNameExclusionFactory.InnerNameExclusion;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(InnerNameExclusion.class)
public class RequestInfoComponentGeneratedAiTests {

    private RequestInfoComponent requestInfoComponent;

    @BeforeEach
    void setUp() {
        requestInfoComponent = new RequestInfoComponent();
    }

    @Test
    void getUserId_returnsUserId() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The getUserId() method is called.
        // THEN: The userId is returned.
        String userId = requestInfoComponent.getUserId();
        assertEquals("userId", userId);
    }

    @Test
    void setUserId_updatesUserId() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setUserId() method is called with a new userId.
        // THEN: The userId is updated.
        requestInfoComponent.setUserId("newUserId");
        assertEquals("newUserId", requestInfoComponent.getUserId());
    }

    @Test
    void getUserEmail_returnsUserEmail() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The getUserEmail() method is called.
        // THEN: The userEmail is returned.
        String userEmail = requestInfoComponent.getUserEmail();
        assertEquals("userEmail", userEmail);
    }

    @Test
    void setUserEmail_updatesUserEmail() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setUserEmail() method is called with a new userEmail.
        // THEN: The userEmail is updated.
        requestInfoComponent.setUserEmail("newEmail");
        assertEquals("newEmail", requestInfoComponent.getUserEmail());
    }

    @Test
    void isRefreshToken_returnsRefreshTokenStatus() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The isRefreshToken() method is called.
        // THEN: The refreshToken status is returned.
        boolean refreshToken = requestInfoComponent.isRefreshToken();
        assertEquals(true, refreshToken);
    }

    @Test
    void setRefreshToken_updatesRefreshTokenStatus() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setRefreshToken() method is called with a new refreshToken status.
        // THEN: The refreshToken status is updated.
        requestInfoComponent.setRefreshToken(false);
        assertEquals(false, requestInfoComponent.isRefreshToken());
    }

    @Test
    void getPath_returnsPath() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The getPath() method is called.
        // THEN: The path is returned.
        String path = requestInfoComponent.getPath();
        assertEquals("path", path);
    }

    @Test
    void setPath_updatesPath() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setPath() method is called with a new path.
        // THEN: The path is updated.
        requestInfoComponent.setPath("newPath");
        assertEquals("newPath", requestInfoComponent.getPath());
    }

    @Test
    void getHttpMethod_returnsHttpMethod() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The getHttpMethod() method is called.
        // THEN: The httpMethod is returned.
        String httpMethod = requestInfoComponent.getHttpMethod();
        assertEquals("httpMethod", httpMethod);
    }

    @Test
    void setHttpMethod_updatesHttpMethod() {
        // GIVEN: A new RequestInfoComponent instance is created.
        // WHEN: The setHttpMethod() method is called with a new httpMethod.
        // THEN: The httpMethod is updated.
        requestInfoComponent.setHttpMethod("newHttpMethod");
        assertEquals("newHttpMethod", requestInfoComponent.getHttpMethod());
    }
}
