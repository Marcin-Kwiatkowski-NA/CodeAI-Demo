package com.bestpractice.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class InterceptorControllerGeneratedAiTests {

  private InterceptorController interceptorController;
  private AuthComponent authComponent;
  private RequestInfoComponent requestInfo;

  @BeforeEach
  void setUp() {
    authComponent = new AuthComponent();
    requestInfo = new RequestInfoComponent();
    interceptorController = new InterceptorController(authComponent, requestInfo);
  }

  @Test
  void preHandle_whenRequestIsAuthorized_thenUserIdAndUserEmailAreSet() {
    // GIVEN
    String bearerToken = "Bearer validToken";
    DecodedJWT decodedJWT = authComponent.decodeJwt(bearerToken);
    String userId = decodedJWT.getSubject();
    String userEmail = decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString();

    // WHEN
    interceptorController.preHandle(null, null, null);

    // THEN
    assertEquals(userId, requestInfo.getUserId());
    assertEquals(userEmail, requestInfo.getUserEmail());
  }

  @Test
  void preHandle_whenRequestIsUnauthorized_thenUnAuthorizedExceptionIsThrown() {
    // GIVEN
    String bearerToken = "Bearer invalidToken";

    // WHEN
    // THEN
    UnAuthorized exception = assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(null, null, null));
    assertEquals("Authorization header is empty", exception.getMessage());
  }

  @Test
  void preHandle_whenRequestPathStartsWithErrorPath_thenReturnFalse() {
    // GIVEN
    String springErrorPath = "error";
    interceptorController.preHandle(null, null, null);

    // WHEN
    // THEN
    assertTrue(interceptorController.preHandle(null, null, null));
  }

  @Test
  void preHandle_whenRequestPathIsDisabledAuthEndpoint_thenReturnFalse() {
    // GIVEN
    String disableAuthEndpoint = "/api/v1/auth";
    interceptorController.preHandle(null, null, null);

    // WHEN
    // THEN
    assertTrue(interceptorController.preHandle(null, null, null));
  }

  @Test
  void preHandle_whenRequestPathIsAuthorizedAndNotErrorPathOrDisabledEndpoint_thenReturnTrue() {
    // GIVEN
    String authorizedPath = "/api/v1/user";
    DecodedJWT decodedJWT = authComponent.decodeJwt("Bearer validToken");
    String userId = decodedJWT.getSubject();
    String userEmail = decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString();

    // WHEN
    interceptorController.preHandle(null, null, null);

    // THEN
    assertTrue(interceptorController.preHandle(null, null, null));
    assertEquals(userId, requestInfo.getUserId());
    assertEquals(userEmail, requestInfo.getUserEmail());
  }
}
