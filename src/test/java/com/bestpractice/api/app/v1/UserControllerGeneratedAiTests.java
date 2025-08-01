package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerGeneratedAiTests {

  private UserController userController;
  private UserService userService;
  private UserRequest userRequest;
  private UserResponse userResponse;

  @BeforeEach
  void setUp() {
    // Initialize mocks and objects
    userService = mock(UserService.class);
    userRequest = new UserRequest();
    userResponse = new UserResponse("1", "testUser", "test@example.com");
    userController = new UserController(userService);
  }

  @Test
  void createUser_validRequest_returnsUserResponse() {
    // GIVEN
    when(userService.generateUser(userRequest)).thenReturn(userResponse);

    // WHEN
    String result = userController.createUser(userRequest, null);

    // THEN
    assertEquals("1", result.getId());
    assertEquals("testUser", result.getUsername());
    assertEquals("test@example.com", result.getEmail());
  }

  @Test
  void createUser_invalidRequest_throwsBadRequest() {
    // GIVEN
    when(userService.generateUser(userRequest)).thenThrow(new BadRequest(""));

    // WHEN
    // THEN
    assertThrows(BadRequest.class, () -> userController.createUser(userRequest, null));
  }
}
