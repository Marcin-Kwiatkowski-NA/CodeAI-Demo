package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
class RequestInfoComponentGeneratedAiTests {

  private RequestInfoComponent requestInfoComponent;

  @BeforeEach
  void setUp() {
    requestInfoComponent = new RequestInfoComponent();
  }

  @Test
  void getUserId_returnsUserId() {
    String userId = requestInfoComponent.getUserId();
    assertEquals("userId", userId);
  }

  @Test
  void setUserId_updatesUserId() {
    requestInfoComponent.setUserId("newUserId");
    assertEquals("newUserId", requestInfoComponent.getUserId());
  }

  @Test
  void getUserEmail_returnsUserEmail() {
    String userEmail = requestInfoComponent.getUserEmail();
    assertEquals("userEmail", userEmail);
  }

  @Test
  void setUserEmail_updatesUserEmail() {
    requestInfoComponent.setUserEmail("newUserEmail");
    assertEquals("newUserEmail", requestInfoComponent.getUserEmail());
  }

  @Test
  void isRefreshToken_returnsRefreshTokenStatus() {
    boolean refreshToken = requestInfoComponent.isRefreshToken();
    assertEquals(true, refreshToken);
  }

  @Test
  void setRefreshToken_updatesRefreshTokenStatus() {
    requestInfoComponent.setRefreshToken(true);
    assertEquals(true, requestInfoComponent.isRefreshToken());
  }

  @Test
  void getPath_returnsPath() {
    String path = requestInfoComponent.getPath();
    assertEquals("path", path);
  }

  @Test
  void setPath_updatesPath() {
    requestInfoComponent.setPath("newPath");
    assertEquals("newPath", requestInfoComponent.getPath());
  }

  @Test
  void getHttpMethod_returnsHttpMethod() {
    String httpMethod = requestInfoComponent.getHttpMethod();
    assertEquals("httpMethod", httpMethod);
  }

  @Test
  void setHttpMethod_updatesHttpMethod() {
    requestInfoComponent.setHttpMethod("newHttpMethod");
    assertEquals("newHttpMethod", requestInfoComponent.getHttpMethod());
  }

  @Test
  void getRequestId_returnsRequestId() {
    String requestId = requestInfoComponent.getRequestId();
    assertEquals("requestId", requestId);
  }

  @Test
  void setRequestId_updatesRequestId() {
    requestInfoComponent.setRequestId("newRequestId");
    assertEquals("newRequestId", requestInfoComponent.getRequestId());
  }
}
