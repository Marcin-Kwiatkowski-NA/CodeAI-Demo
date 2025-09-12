package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.springframework.validation.BindingResult;

class UserControllerGeneratedAiTests {

  private UserController userController;
  private UserService userService;

  @BeforeEach
  void setUp() {
    UserService userService = new UserService();
    this.userService = userService;
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
    UserResponse response = userController.createUser(request, new BindingResult());

    // THEN
    assert response != null;
    assert response.getId() != null;
    assert response.getUsername().equals("testUser");
    assert response.getEmail().equals("test@example.com");
  }

  @Test
  void createUser_invalidRequest_throwsBadRequest() {
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
