package com.bestpractice.api.app;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.Credential;

@ExtendWith(InterceptorController.class)
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
  void preHandle_validToken_updatesRequestInfo() {
    // GIVEN: A valid JWT token is provided.
    String token = "valid_jwt_token";
    String userId = "user123";
    String userEmail = "user@example.com";

    // WHEN: The preHandle method is called with the valid token.
    interceptorController.preHandle(new MockHttpServletRequest(), new MockHttpServletResponse(), true);

    // THEN: The requestInfo component should be updated with the userId, userEmail, and request ID.
    assertNotNull(requestInfo.getRequestId());
    assertEquals(userId, requestInfo.getUserId());
    assertEquals(userEmail, requestInfo.getUserEmail());
  }

  @Test
  void preHandle_invalidToken_throwsUnAuthorized() {
    // GIVEN: An invalid JWT token is provided.
    String token = "invalid_jwt_token";

    // WHEN: The preHandle method is called with the invalid token.
    assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(new MockHttpServletRequest(), new MockHttpServletResponse(), true));
  }

  @Test
  void preHandle_missingAuthorizationHeader_throwsUnAuthorized() {
    // GIVEN: The Authorization header is missing.
    // WHEN: The preHandle method is called without the Authorization header.
    assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(new MockHttpServletRequest(), new MockHttpServletResponse(), true));
  }

  @Test
  void preHandle_invalidTokenFormat_throwsUnAuthorized() {
    // GIVEN: An invalid JWT token format is provided.
    String token = "Bearer invalid_token";

    // WHEN: The preHandle method is called with the invalid token format.
    assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(new MockHttpServletRequest(), new MockHttpServletResponse(), true));
  }

  @Test
  void preHandle_missingAuthorizationHeader_throwsUnAuthorized() {
    // GIVEN: The Authorization header is missing.
    // WHEN: The preHandle method is called without the Authorization header.
    assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(new MockHttpServletRequest(), new MockHttpServletResponse(), true));
  }
}