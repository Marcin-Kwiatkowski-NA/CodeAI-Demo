package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoExtension;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RequestInfoComponentGeneratedAiTests {

  @InjectMocks
  private RequestInfoComponent requestInfoComponent;

  @BeforeEach
  void setUp() {
    requestInfoComponent = new RequestInfoComponent();
  }

  @Test
  void getUserId() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The getUserId() method is called.
    // THEN: The userId property is returned.
    assertEquals("testUserId", requestInfoComponent.getUserId());
  }

  @Test
  void getUserEmail() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The getUserEmail() method is called.
    // THEN: The userEmail property is returned.
    assertEquals("testUserEmail", requestInfoComponent.getUserEmail());
  }

  @Test
  void isRefreshToken() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The isRefreshToken() method is called.
    // THEN: The isRefreshToken property is returned.
    assertTrue(requestInfoComponent.isRefreshToken());
  }

  @Test
  void getPath() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The getPath() method is called.
    // THEN: The path property is returned.
    assertEquals("/api/users", requestInfoComponent.getPath());
  }

  @Test
  void getHttpMethod() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The getHttpMethod() method is called.
    // THEN: The httpMethod property is returned.
    assertEquals("GET", requestInfoComponent.getHttpMethod());
  }

  @Test
  void getRequestId() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The getRequestId() method is called.
    // THEN: The requestId property is returned.
    assertEquals("request123", requestInfoComponent.getRequestId());
  }
}
