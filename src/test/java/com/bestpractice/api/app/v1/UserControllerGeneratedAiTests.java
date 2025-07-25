package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ValueAssertionExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.validation.BindingResult;

@ExtendWith(ValueAssertionExtension.class)
class UserControllerGeneratedAiTests {

  private UserController userController;
  private UserService userService;

  @BeforeEach
  void setUp() {
    UserService userService = new UserService() {};
    this.userController = new UserController(userService);
  }

  @Test
  void createUser_validRequest_returnsUserResponse() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername("testUser");
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    UserResponse response = this.userController.createUser(request, new BindingResult());

    // THEN
    assertNotNull(response);
    assertEquals("testUser", response.getUsername());
    assertEquals("test@example.com", response.getEmail());
  }

  @Test
  void createUser_invalidRequest_throwsBadRequest() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername(null);
    request.setEmail(null);
    request.setPassword(null);

    // WHEN
    BindingResult bdResult = new BindingResult();
    bdResult.addError("username", "Username is required");
    bdResult.addError("email", "Email is required");

    // THEN
    assertThrows(BadRequest.class, () -> this.userController.createUser(request, bdResult));
  }
}
