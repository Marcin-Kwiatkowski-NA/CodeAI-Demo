package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;

public class UserControllerGeneratedAiTests {

  @org.mockito.Mockito.mock
  private UserService userService;

  private UserController userController;

  @BeforeEach
  void setUp() {
    userController = new UserController(userService);
  }

  @Test
  void createUser_whenRequestIsValid_thenReturnUserResponse() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername("testUser");
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    Mockito.doReturn(new UserResponse("1", "testUser", "test@example.com"))
        .when(userService).generateUser(request);

    // THEN
    UserResponse response = userController.createUser(request, new BindingResult());
    assertEquals("1", response.getId());
    assertEquals("testUser", response.getUsername());
    assertEquals("test@example.com", response.getEmail());
  }

  @Test
  void createUser_whenRequestHasErrors_thenThrowsBadRequest() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername(null);
    request.setEmail(null);
    request.setPassword(null);

    // WHEN
    Mockito.doThrow(new BadRequest("")).when(userService).generateUser(request);

    // THEN
    assertThrows(BadRequest.class, () -> userController.createUser(request, new BindingResult()));
  }
}
