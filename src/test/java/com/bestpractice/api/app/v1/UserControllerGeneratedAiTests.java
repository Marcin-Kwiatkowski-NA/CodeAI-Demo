package com.bestpractice.api.app.v1;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerGeneratedAiTests {

  private UserController userController;
  private UserService userServiceMock;

  @BeforeEach
  void setUp() {
    // Mock the UserService
    userServiceMock = Mockito.mock(UserService.class);
    // Inject the mock into the controller
    this.userController = new UserController(userServiceMock);
  }

  @Test
  void createUser_whenRequestIsValid_thenShouldGenerateUserAndReturnResponse() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername("testuser");
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    UserResponse response = this.userController.createUser(request, null);

    // THEN
    Mockito.verify(userServiceMock, Mockito.times(1)).generateUser(request);
    assertEquals("testuser", response.getUsername());
    assertEquals("test@example.com", response.getEmail());
  }

  @Test
  void createUser_whenRequestHasErrors_thenShouldThrowBadRequest() {
    // GIVEN
    UserRequest request = new UserRequest();
    request.setUsername("");
    request.setEmail("");
    request.setPassword("");

    // WHEN
    // Reset the mock behavior
    Mockito.reset(userServiceMock);
    // Expect a BadRequest to be thrown
    Mockito.doThrow(new BadRequest())
        .when(userServiceMock).generateUser(request);

    // THEN
    // Expect the exception to be thrown
    assertThrows(BadRequest.class, () -> this.userController.createUser(request, null));
  }
}