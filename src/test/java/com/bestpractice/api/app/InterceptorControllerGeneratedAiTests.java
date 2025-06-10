package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InterceptorControllerGeneratedAiTests {

  @InjectMocks
  private InterceptorController interceptorController;

  @Mock
  private AuthComponent authComponent;

  @Mock
  private RequestInfoComponent requestInfo;

  @Mock
  private HttpServletRequest httpServletRequest;

  @Mock
  private HttpServletResponse httpServletResponse;

  @BeforeEach
  public void setUp() {
    interceptorController = new InterceptorController(authComponent, requestInfo);
  }

  @Test
  public void testPreHandle_ValidToken() throws IOException {
    // GIVEN
    String token = "Bearer valid_token";
    List<String> headers = new ArrayList<>();
    headers.add(token);
    when(httpServletRequest.getHeaders("Authorization")).thenReturn((Enumeration<String>) (Enumeration) headers.listIterator());
    DecodedJWT decodedJWT = mock(DecodedJWT.class);
    when(decodedJWT.getSubject()).thenReturn("user_id");
    com.auth0.jwt.interfaces.Claim claim = new com.auth0.jwt.impl.JWTCreator.Builder().build().verify(token.replace("Bearer", "").trim());
    when(decodedJWT.getClaim(anyString())).thenReturn(claim);
    when(authComponent.decodeJwt(token.replace("Bearer", "").trim())).thenReturn(decodedJWT);

    // WHEN
    boolean result = interceptorController.preHandle(httpServletRequest, httpServletResponse, null);

    // THEN
    assertTrue(result);
  }

  @Test
  public void testPreHandle_NoAuthorizationHeader() {
    // GIVEN
    when(httpServletRequest.getHeaders("Authorization")).thenReturn(List.of().listIterator());

    // WHEN
    UnAuthorized exception = assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(httpServletRequest, httpServletResponse, null));

    // THEN
    assertEquals("Authorization header is empty", exception.getMessage());
  }

  @Test
  public void testPreHandle_InvalidTokenFormat() {
    // GIVEN
    String token = "invalid_token";
    List<String> headers = new ArrayList<>();
    headers.add(token);
    when(httpServletRequest.getHeaders("Authorization")).thenReturn((Enumeration<String>) (Enumeration) headers.listIterator());

    // WHEN
    UnAuthorized exception = assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(httpServletRequest, httpServletResponse, null));

    // THEN
    assertEquals("Authorization supports Bearer format", exception.getMessage());
  }

  @Test
  public void testPreHandle_InvalidToken() {
    // GIVEN
    String token = "Bearer invalid_token";
    List<String> headers = new ArrayList<>();
    headers.add(token);
    when(httpServletRequest.getHeaders("Authorization")).thenReturn((Enumeration<String>) (Enumeration) headers.listIterator());
    when(authComponent.decodeJwt(anyString())).thenThrow(new UnAuthorized("Invalid token"));

    // WHEN
    UnAuthorized exception = assertThrows(UnAuthorized.class, () -> interceptorController.preHandle(httpServletRequest, httpServletResponse, null));

    // THEN
    assertEquals("Invalid token", exception.getMessage());
  }

  @Test
  public void testPreHandle_DisabledAuthEndpoints() {
    // GIVEN
    when(httpServletRequest.getRequestURI()).thenReturn("/api/v1/user");

    // WHEN
    boolean result = interceptorController.preHandle(httpServletRequest, httpServletResponse, null);

    // THEN
    assertTrue(result);
  }

  @Test
  public void testPreHandle_PathStartsWithError() {
    // GIVEN
    when(httpServletRequest.getRequest```java
URI()).thenReturn("/error");

    // WHEN
    boolean result = interceptorController.preHandle(httpServletRequest, httpServletResponse, null);

    // THEN
    assertFalse(result);
  }
}