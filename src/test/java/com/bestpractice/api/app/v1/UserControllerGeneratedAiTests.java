package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.JustifiedMockitoExtension;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(JustifiedMockitoExtension.class)
class UserControllerGeneratedAiTests {

  @Mock
  private UserService userService;

  @Spy
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
    String id = "123";
    String encodePw = "encodedPassword";
    UserResponse response = userController.createUser(request, null);

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
    BindingResult bdResult = new BindingResult();
    bdResult.addError("username", "Username is required");
    bdResult.addError("email", "Email is required");

    // THEN
    when(userService.generateUser(any())).thenReturn(new UserResponse("","",""));
    
    assertThrows(BadRequest.class, () -> userController.createUser(request, bdResult));
  }
}
