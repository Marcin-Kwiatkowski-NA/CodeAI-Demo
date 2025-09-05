package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.common.exception.BadRequest;
import org.springframework.validation.BindingResult;

class UserControllerGeneratedAiTests {

  private UserController userController;
  private UserService userService;

  @BeforeEach
  void setUp() {
    UserService userService = new UserService() {
      @Override
      public UserResponse generateUser(UserRequest request) {
        User user = request.convert("123", "encodedPassword");
        return new UserResponse("123", user.getUsername(), user.getEmail());
      }
    };
    this.userController = new UserController(userService);
  }

  @Test
  void createUser_whenRequestIsValid_thenReturnUserResponse() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername("testUser");
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    UserResponse response = userController.createUser(request, new BindingResult());

    // THEN
    assertNotNull(response);
    assertEquals("testUser", response.getUsername());
    assertEquals("test@example.com", response.getEmail());
    assertEquals("123", response.getId());
  }

  @Test
  void createUser_whenRequestHasErrors_thenThrowsBadRequest() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername("");
    request.setEmail("");
    request.setPassword("");

    // WHEN
    // THEN
    assertThrows(BadRequest.class, () -> userController.createUser(request, new BindingResult()));
  }
}
