package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class UserServiceImplGeneratedAiTests {

  private UserServiceImpl userService;
  private UserPersistentRepository mockRepository;
  private BCryptPasswordEncryptionComponent mockEncryptionComponent;

  @BeforeEach
  void setUp() {
    mockRepository = new UserPersistentRepository() {
      @Override
      public UserById findById(String id) throws Exception {
        return new User("test", "test");
      }

      @Override
      public UserByEmail findByEmail(String email) throws Exception {
        return new User("test", "test");
      }

      @Override
      public User newId() {
        return new User("test", "test");
      }

      @Override
      public User insert(User user) throws Exception {
        return user;
      }
    };
    mockEncryptionComponent = new BCryptPasswordEncryptionComponent();
    userService = new UserServiceImpl(mockEncryptionComponent, mockEncryptionComponent);
  }

  @Test
  void getUserById_validId_returnsUser() {
    // GIVEN: A valid user ID
    String id = "123";

    // WHEN: The getUserById method is called with the valid ID
    User user = userService.getUserById(id);

    // THEN: A user object is returned
    assertNotNull(user);
    assertEquals("test", user.getUsername());
    assertEquals("test", user.getEmail());
  }

  @Test
  void getAuthenticatedUser_validEmailAndPassword_returnsUser() {
    // GIVEN: Valid email and password
    String email = "test@example.com";
    String rawPw = "password";

    // WHEN: The getAuthenticatedUser method is called with the valid email and password
    User user = userService.getAuthenticatedUser(email, rawPw);

    // THEN: The user object is returned
    assertNotNull(user);
    assertEquals("test", user.getUsername());
    assertEquals("test", user.getEmail());
  }

  @Test
  void generateUser_validRequest_returnsUserResponse() {
    // GIVEN: A valid UserRequest
    UserRequest request = new UserRequest("test", "password");

    // WHEN: The generateUser method is called with the UserRequest
    UserResponse response = userService.generateUser(request);

    // THEN: A UserResponse object is returned
    assertNotNull(response);
    assertEquals("123", response.getId());
    assertEquals("test", response.getUsername());
    assertEquals("test", response.getEmail());
  }

  @Test
  void getUserByEmail_validEmail_returnsUser() {
    // GIVEN: Valid email
    String email = "test@example.com";

    // WHEN: The getUserByEmail method is called with the valid email
    User user = userService.getUserByEmail(email);

    // THEN: A user object is returned
    assertNotNull(user);
    assertEquals("test", user.getUsername());
    assertEquals("test", user.getEmail());
  }
}
