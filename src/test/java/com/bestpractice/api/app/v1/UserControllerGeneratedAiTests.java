package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import com.bestpractice.api.infrastrucuture.entity.User;

@ExtendWith(MockitoJUnitRunner.class)
public class UserControllerGeneratedAiTests {

  @Mockito.Mock
  private UserService userService;

  @Mockito.InjectMocks
  private UserController userController;

  @BeforeEach
  void setUp() {
    userController = new UserController(userService);
    Mockito.reset(userService);
  }

  @Test
  void createUser_validRequest_returnsUserResponse() {
    UserRequest request = new UserRequest();
    request.setUsername("testUser");
    request.setEmail("test@example.com");
    request.setPassword("password123");

    UserResponse response = userController.createUser(request, null);

    assertEquals("testUser", response.getUsername());
    assertEquals("test@example.com", response.getEmail());
  }

  @Test
  void createUser_invalidRequest_throwsBadRequestException() {
    UserRequest request = new UserRequest();
    request.setUsername(null);
    request.setEmail(null);
    request.setPassword(null);

    assertThrows(BadRequest.class, () -> userController.createUser(request, null));
  }
}
