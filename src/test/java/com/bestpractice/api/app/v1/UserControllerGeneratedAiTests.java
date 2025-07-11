package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.springframework.validation.BindingResult;

@ExtendWith(UserControllerGeneratedAiTests.class)
class UserControllerGeneratedAiTests {

  private UserController userController;
  private UserService userService;
  private UserRequest userRequest;
  private UserResponse userResponse;

  @BeforeEach
  void setUp() {
    // Initialize UserService with a mock implementation for testing purposes.
    UserService userServiceMock = new UserService() {
      @Override
      public UserResponse generateUser(UserRequest request) {
        return new UserResponse("123", "test", "test@example.com");
      }
    };
    this.userService = userServiceMock;
    this.userRequest = new UserRequest();
    this.userRequest.setUsername("testUser");
    this.userRequest.setEmail("test@example.com");
    this.userRequest.setPassword("password");
    this.userController = new UserController(this.userService);
  }

  @Test
  void createUser_validRequest_returnsUserResponse() {
    // GIVEN: A valid UserRequest is provided.
    // WHEN: The createUser method is called.
    // THEN: A UserResponse is returned with the generated user data.
    UserResponse actualResponse = this.userController.createUser(this.userRequest, new BindingResult());
    // Assert that the response is not null
    assert actualResponse != null;
    // Assert that the id is "123"
    assert actualResponse.getId().equals("123") : "ID should be 123";
    // Assert that the username is "testUser"
    assert actualResponse.getUsername().equals("testUser") : "Username should be testUser";
    // Assert that the email is "test@example.com"
    assert actualResponse.getEmail().equals("test@example.com") : "Email should be test@example.com";
  }

  @Test
  void createUser_invalidRequest_throwsBadRequest() {
    // GIVEN: An invalid UserRequest is provided (e.g., missing required fields).
    this.userRequest.setUsername(null);
    this.userRequest.setEmail(null);
    this.userRequest.setPassword(null);

    // WHEN: The createUser method is called.
    // THEN: A BadRequest exception is thrown.
    BadRequest exception = assertThrows(BadRequest.class, () -> {
      this.userController.createUser(this.userRequest, new BindingResult());
    });

    // Assert that the exception message is correct.
    assert exception.getMessage().contains("username") : "Error message should contain username";
  }
}
