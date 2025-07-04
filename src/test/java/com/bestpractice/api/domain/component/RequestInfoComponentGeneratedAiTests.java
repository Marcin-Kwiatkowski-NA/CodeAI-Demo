package com.bestpractice.api.domain.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.InnerAutoDetectExtensionFactory;
import org.junit.jupiter.api.extension.InnerExtensionInvocationSpec;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(InnerAutoDetectExtensionFactory.class)
public class RequestInfoComponentGeneratedAiTests {

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
    String userId = requestInfoComponent.getUserId();
    assert userId != null;
  }

  @Test
  void setUserId() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The setUserId() method is called with a non-null value.
    // THEN: The userId property is set to the provided value.
    requestInfoComponent.setUserId("testUserId");
    assert requestInfoComponent.getUserId() == "testUserId";
  }

  @Test
  void getUserEmail() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The getUserEmail() method is called.
    // THEN: The userEmail property is returned.
    String userEmail = requestInfoComponent.getUserEmail();
    assert userEmail != null;
  }

  @Test
  void setUserEmail() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The setUserEmail() method is called with a non-null value.
    // THEN: The userEmail property is set to the provided value.
    requestInfoComponent.setUserEmail("testEmail");
    assert requestInfoComponent.getUserEmail() == "testEmail";
  }

  @Test
  void isRefreshToken() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The isRefreshToken() method is called.
    // THEN: The isRefreshToken property is returned.
    boolean refreshToken = requestInfoComponent.isRefreshToken();
    assert refreshToken == true;
  }

  @Test
  void setRefreshToken() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The setRefreshToken() method is called with a boolean value.
    // THEN: The isRefreshToken property is set to the provided value.
    requestInfoComponent.setRefreshToken(true);
    assert requestInfoComponent.isRefreshToken() == true;
  }

  @Test
  void getPath() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The getPath() method is called.
    // THEN: The path property is returned.
    String path = requestInfoComponent.getPath();
    assert path != null;
  }

  @Test
  void setPath() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The setPath() method is called with a non-null value.
    // THEN: The path property is set to the provided value.
    requestInfoComponent.setPath("testPath");
    assert requestInfoComponent.getPath() == "testPath";
  }

  @Test
  void getHttpMethod() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The getHttpMethod() method is called.
    // THEN: The httpMethod property is returned.
    String httpMethod = requestInfoComponent.getHttpMethod();
    assert httpMethod != null;
  }

  @Test
  void setHttpMethod() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The setHttpMethod() method is called with a non-null value.
    // THEN: The httpMethod property is set to the provided value.
    requestInfoComponent.setHttpMethod("GET");
    assert requestInfoComponent.getHttpMethod() == "GET";
    }

  @Test
  void getRequestId() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The getRequestId() method is called.
    // THEN: The requestId property is returned.
    String requestId = requestInfoComponent.getRequestId();
    assert requestId != null;
  }

  @Test
  void setRequestId() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The setRequestId() method is called with a non-null value.
    // THEN: The requestId property is set to the provided value.
    requestInfoComponent.setRequestId("testRequestId");
    assert requestInfoComponent.getRequestId() == "testRequestId";
  }


}
