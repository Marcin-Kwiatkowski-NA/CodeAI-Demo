package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;

class UserControllerGeneratedAiTests {

  private UserController userController;
  private UserService userService;

  @BeforeEach
  void setUp() {
    UserService mockUserService = new UserService() {
      @Override
      public UserResponse generateUser(UserRequest request) {
        return new UserResponse("123", "testUser", "test@example.com");
      }
    };
    this.userService = mockUserService;
    this.userController = new UserController(this.userService);
  }

  @Test
  void createUser_validRequest_returnsUserResponse() {
    // GIVEN: A valid UserRequest is provided.
    UserRequest request = new UserRequest();
    request.setUsername("testUser");
    request.setEmail("test@example.com");
    request.setPassword("password");

    // WHEN: The createUser method is called with the request.
    UserResponse response = this.userController.createUser(request, new BindingResult());

    // THEN: A UserResponse is returned with the expected data.
    assertEquals("123", response.getId());
    assertEquals("testUser", response.getUsername());
    assertEquals("test@example.com", response.getEmail());
  }

  @Test
  void createUser_invalidRequest_throwsBadRequest() {
    // GIVEN: An invalid UserRequest is provided.
    UserRequest request = new UserRequest();
    request.setUsername("");
    request.setEmail("");
    request.setPassword("");

    // WHEN: The createUser method is called with the request.
    // THEN: A BadRequest exception is thrown.
    assertThrows(BadRequest.class, () -> this.userController.createUser(request, new BindingResult()));
  }
}
