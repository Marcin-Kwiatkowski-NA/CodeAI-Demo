package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerGeneratedAiTests {

  private UserController userController;
  private UserService userService;
  private UserRequest request;
  private UserResponse response;

  @BeforeEach
  void setUp() {
    // Mocking the userService dependency
    userService = Mockito.mock(UserService.class);
    userController = new UserController(userService);
    request = new UserRequest();
  }

  @Test
  void createUser_validRequest_returnsUserResponse() {
    // GIVEN
    Mockito.doReturn(new UserResponse("123", "testUser", "test@example.com"))
        .when(userService)
        .generateUser(request);

    // WHEN
    response = userController.createUser(request, null);

    // THEN
    assertEquals("123", response.getId());
    assertEquals("testUser", response.getUsername());
    assertEquals("test@example.com", response.getEmail());
  }

  @Test
  void createUser_invalidRequest_throwsBadRequest() {
    // GIVEN
    Mockito.doThrow(new BadRequest("Validation failed"))
        .when(userService)
        .generateUser(request);

    // WHEN
    assertThrows(BadRequest.class, () -> userController.createUser(request, null));

    // THEN
  }
}
