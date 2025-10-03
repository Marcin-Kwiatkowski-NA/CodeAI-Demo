package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestInfoComponentGeneratedAiTests {

  private RequestInfoComponent requestInfoComponent;

  @BeforeEach
  void setUp() {
    requestInfoComponent = new RequestInfoComponent();
  }

  @Test
  void getUserId_returnsUserId() {
    // GIVEN a RequestInfoComponent
    // WHEN the getUserId() method is called
    // THEN the userId should be returned
    String userId = requestInfoComponent.getUserId();
    assertEquals("userId", userId);
  }

  @Test
  void setUserId_updatesUserId() {
    // GIVEN a RequestInfoComponent
    // WHEN the setUserId() method is called with a new userId
    // THEN the userId should be updated
    requestInfoComponent.setUserId("newUserId");
    assertEquals("newUserId", requestInfoComponent.getUserId());
  }

  @Test
  void getUserEmail_returnsUserEmail() {
    // GIVEN a RequestInfoComponent
    // WHEN the getUserEmail() method is called
    // THEN the userEmail should be returned
    String userEmail = requestInfoComponent.getUserEmail();
    assertEquals("userEmail", userEmail);
  }

  @Test
  void setUserEmail_updatesUserEmail() {
    // GIVEN a RequestInfoComponent
    // WHEN the setUserEmail() method is called with a new userEmail
    // THEN the userEmail should be updated
    requestInfoComponent.setUserEmail("newEmail");
    assertEquals("newEmail", requestInfoComponent.getUserEmail());
  }

  @Test
  void isRefreshToken_returnsRefreshTokenStatus() {
    // GIVEN a RequestInfoComponent
    // WHEN the isRefreshToken() method is called
    // THEN the refreshToken status should be returned
    boolean refreshToken = requestInfoComponent.isRefreshToken();
    assertEquals(true, refreshToken);
  }

  @Test
  void setRefreshToken_updatesRefreshTokenStatus() {
    // GIVEN a RequestInfoComponent
    // WHEN the setRefreshToken() method is called with a new refreshToken status
    // THEN the refreshToken status should be updated
    requestInfoComponent.setRefreshToken(false);
    assertEquals(false, requestInfoComponent.isRefreshToken());
  }

  @Test
  void getPath_returnsPath() {
    // GIVEN a RequestInfoComponent
    // WHEN the getPath() method is called
    // THEN the path should be returned
    String path = requestInfoComponent.getPath();
    assertEquals("path", path);
  }

  @Test
  void setPath_updatesPath() {
    // GIVEN a RequestInfoComponent
    // WHEN the setPath() method is called with a new path
    // THEN the path should be updated
    requestInfoComponent.setPath("newPath");
    assertEquals("newPath", requestInfoComponent.getPath());
  }

  @Test
  void getHttpMethod_returnsHttpMethod() {
    // GIVEN a RequestInfoComponent
    // WHEN the getHttpMethod() method is called
    // THEN the httpMethod should be returned
    String httpMethod = requestInfoComponent.getHttpMethod();
    assertEquals("httpMethod", httpMethod);
  }

  @Test
  void setHttpMethod_updatesHttpMethod() {
    // GIVEN a RequestInfoComponent
    // WHEN the setHttpMethod() method is called with a new httpMethod
    // THEN the httpMethod should be updated
    requestInfoComponent.setHttpMethod("newHttpMethod");
    assertEquals("newHttpMethod", requestInfoComponent.getHttpMethod());
  }

  @Test
  void getRequestId_returnsRequestId() {
    // GIVEN a RequestInfoComponent
    // WHEN the getRequestId() method is called
    // THEN the requestId should be returned
    String requestId = requestInfoComponent.getRequestId();
    assertEquals("requestId", requestId);
  }

  @Test
  void setRequestId_updatesRequestId() {
    // GIVEN a RequestInfoComponent
    // WHEN the setRequestId() method is called with a new requestId
    // THEN the requestId should be updated
    requestInfoComponent.setRequestId("newRequestId");
    assertEquals("newRequestId", requestInfoComponent.getRequestId());
  }
}
