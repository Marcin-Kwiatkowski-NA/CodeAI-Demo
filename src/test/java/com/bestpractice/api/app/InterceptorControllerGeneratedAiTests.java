package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.common.exception.UnAuthorized;

import java.util.UUID;

@ExtendWith(InterceptorControllerGeneratedAiTests.class)
class InterceptorControllerGeneratedAiTests {

    private InterceptorController interceptorController;
    private AuthComponent authComponent;
    private RequestInfoComponent requestInfo;
    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
        authComponent = new AuthComponent(credentialProperty);
        requestInfo = new RequestInfoComponent();
        interceptorController = new InterceptorController(authComponent, requestInfo);
    }

    @Test
    void preHandle_validToken_shouldSetRequestInfo() {
        // GIVEN
        String validToken = "Bearer validToken";
        String userId = "user123";
        String userEmail = "user@example.com";

        // WHEN
        interceptorController.preHandle(null, null, null);

        // THEN
        assertNotNull(requestInfo.getRequestId(), "Request ID should be set");
        assertEquals(userId, requestInfo.getUserId(), "User ID should be set");
        assertEquals(userEmail, requestInfo.getUserEmail(), "User Email should be set");
    }

    @Test
    void preHandle_invalidToken_shouldThrowUnAuthorized() {
        // GIVEN
        String invalidToken = "Bearer invalidToken";

        // WHEN
        // THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> {
            interceptorController.preHandle(null, null, null);
        });

        assertEquals("Token is expired time", exception.getMessage(), "Exception message should match");
    }

    @Test
    void preHandle_emptyAuthorizationHeader_shouldThrowUnAuthorized() {
        // GIVEN

        // WHEN
        // THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> {
            interceptorController.preHandle(null, null, null);
        });

        assertEquals("Authorization header is empty", exception.getMessage(), "Exception message should match");
    }

    @Test
    void preHandle_invalidTokenFormat_shouldThrowUnAuthorized() {
        // GIVEN
        String invalidToken = "invalidToken";

        // WHEN
        // THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> {
            interceptorController.preHandle(null, null, null);
        });

        assertEquals("Authorization supports Bearer format", exception.getMessage(), "Exception message should match");
    }

    @Test
    void preHandle_noPathPrefix_shouldReturnTrue() {
        // GIVEN
        // WHEN
        // THEN
        assertTrue(interceptorController.preHandle(null, null, null));
    }

    @Test
    void preHandle_pathStartsWithErrorPath_shouldReturnFalse() {
        // GIVEN
        String errorPath = "/api/v1/error";

        // WHEN
        // THEN
        assertFalse(interceptorController.preHandle(null, null, null));
    }
}
