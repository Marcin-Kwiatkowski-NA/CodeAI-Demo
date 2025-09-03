package com.bestpractice.api.domain.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@Service
public class UserServiceImplGeneratedAiTests {

  private final UserPersistentRepository userRepository;
  private final BCryptPasswordEncryptionComponent encryptionComponent;

  public UserServiceImplGeneratedAiTests(UserPersistentRepository userRepository, BCryptPasswordEncryptionComponent encryptionComponent) {
    this.userRepository = userRepository;
    this.encryptionComponent = encryptionComponent;
  }

  @BeforeEach
  public void setUp() {
    // Reset state before each test
  }

  @Test
  @ExtendWith(ExtendWith.class)
  public void getUserById_validId_returnsUser() {
    // GIVEN
    String id = "123";
    UserPersistentRepository mockRepository = new UserPersistentRepository() {
      @Override
      public User findById(String id) {
        return new User(id, "testUser", "test@example.com", "password");
      }
    };
    UserServiceImpl userService = new UserServiceImpl(mockRepository, encryptionComponent);

    // WHEN
    User user = userService.getUserById(id);

    // THEN
    assert user != null;
    assertEquals("123", user.getId());
    assertEquals("testUser", user.getUsername());
    assertEquals("test@example.com", user.getEmail());
    assertEquals("password", user.getPassword());
  }

  @Test
  public void getAuthenticatedUser_validCredentials_returnsUser() {
    // GIVEN
    String email = "test@example.com";
    String rawPw = "password";
    UserPersistentRepository mockRepository = new UserPersistentRepository() {
      @Override
      public User findByEmail(String email) {
        return new User("123", "testUser", email, "password");
      }
    };
    UserServiceImpl userService = new UserServiceImpl(mockRepository, encryptionComponent);

    // WHEN
    User user = userService.getAuthenticatedUser(email, rawPw);

    // THEN
    assert user != null;
    assertEquals("123", user.getId());
    assertEquals("testUser", user.getUsername());
    assertEquals("test@example.com", user.getEmail());
    assertEquals("password", user.getPassword());
  }

  @Test
  public void generateUser_validRequest_returnsUserResponse() {
    // GIVEN
    UserRequest request = new UserRequest() {
      @Override
      public String getPassword() {
        return "newPassword";
      }

      @Override
      public User convert(String id, String encPw) {
        return new User(id, "newUser", "new@example.com", encPw);
      }
    };
    String id = "456";
    UserPersistentRepository mockRepository = new UserPersistentRepository() {
      @Override
      public User newId() {
        return new User(id, "newUser", "new@example.com", "newPassword");
      }

      @Override
      public User insert(User user) {
        return user;
      }
    };
    UserServiceImpl userService = new UserServiceImpl(mockRepository, encryptionComponent);

    // WHEN
    UserResponse userResponse = userService.generateUser(request);

    // THEN
    assert userResponse != null;
    assertEquals("456", userResponse.getId());
    assertEquals("newUser", userResponse.getUsername());
    assertEquals("new@example.com", userResponse.getEmail());
  }
}