package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class RequestInfoComponentGeneratedAiTests {

  private RequestInfoComponent requestInfoComponent;

  @BeforeEach
  void setUp() {
    requestInfoComponent = new RequestInfoComponent();
  }

  @Test
  void getUserId_returnsUserId() {
    assertEquals("userId", requestInfoComponent.getUserId());
  }

  @Test
  void setUserId_updatesUserId() {
    requestInfoComponent.setUserId("newUserId");
    assertEquals("newUserId", requestInfoComponent.getUserId());
  }

  @Test
  void getUserEmail_returnsUserEmail() {
    assertEquals("userEmail", requestInfoComponent.getUserEmail());
  }

  @Test
  void setUserEmail_updatesUserEmail() {
    requestInfoComponent.setUserEmail("newEmail");
    assertEquals("newEmail", requestInfoComponent.getUserEmail());
  }

  @Test
  void isRefreshToken_returnsRefreshTokenStatus() {
    assertEquals(true, requestInfoComponent.isRefreshToken());
  }

  @Test
  void setRefreshToken_updatesRefreshTokenStatus() {
    requestInfoComponent.setRefreshToken(false);
    assertEquals(false, requestInfoComponent.isRefreshToken());
  }

  @Test
  void getPath_returnsPath() {
    assertEquals("path", requestInfoComponent.getPath());
  }

  @Test
  void setPath_updatesPath() {
    requestInfoComponent.setPath("newPath");
    assertEquals("newPath", requestInfoComponent.getPath());
  }

  @Test
  void getHttpMethod_returnsHttpMethod() {
    assertEquals("httpMethod", requestInfoComponent.getHttpMethod());
  }

  @Test
  void setHttpMethod_updatesHttpMethod() {
    requestInfoComponent.setHttpMethod("newHttpMethod");
    assertEquals("newHttpMethod", requestInfoComponent.getHttpMethod());
  }

  @Test
  void getRequestId_returnsRequestId() {
    assertEquals("requestId", requestInfoComponent.getRequestId());
  }

  @Test
  void setRequestId_updatesRequestId() {
    requestInfoComponent.setRequestId("newRequestId");
    assertEquals("newRequestId", requestInfoComponent.getRequestId());
  }
}
