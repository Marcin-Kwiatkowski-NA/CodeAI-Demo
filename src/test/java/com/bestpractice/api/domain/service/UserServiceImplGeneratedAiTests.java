package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(UserServiceImplGeneratedAiTests.class)
class UserServiceImplGeneratedAiTests {

  private UserServiceImpl userServiceImpl;

  @BeforeEach
  void setUp() {
    // Mocking is not needed for this class.
    userServiceImpl = new UserServiceImpl(
        new UserPersistentRepository() {
          @Override
          public UserByIdById findById(String id) throws Exception {
            return null;
          }

          @Override
          public UserByEmailById findByEmail(String email) throws Exception {
            return null;
          }

          @Override
          public User insert(User user) throws Conflict, Exception {
            return null;
          }

          @Override
          public User newId() {
            return null;
          }
        },
        new BCryptPasswordEncryptionComponent() {
          @Override
          public String encodePassword(String rawPassword) {
            return "encodedPassword";
          }

          @Override
          public boolean matchedPassword(String rawPassword, String encryptedPassword) {
            return true;
          }
        }
    );
  }

  @Test
  void getUserById_validId_returnsUser() {
    // GIVEN: A valid user ID.
    String id = "123";

    // WHEN: The getUserById method is called with the valid ID.
    User user = userServiceImpl.getUserById(id);

    // THEN: A user object is returned.
    assertNotNull(user);
  }

  @Test
  void getAuthenticatedUser_validEmailAndPassword_returnsUser() {
    // GIVEN: Valid email and password.
    String email = "test@example.com";
    String rawPw = "password";

    // WHEN: The getAuthenticatedUser method is called with the valid email and password.
    User user = userServiceImpl.getAuthenticatedUser(email, rawPw);

    // THEN: The user object is returned.
    assertNotNull(user);
  }

  @Test
  void generateUser_validRequest_insertsUserAndReturnsResponse() {
    // GIVEN: A valid UserRequest.
    UserRequest request = new UserRequest();
    String email = "test@example.com";
    String password = "password";
    String encPw = "encodedPassword";

    // WHEN: The generateUser method is called with the UserRequest.
    UserResponse response = userServiceImpl.generateUser(request.convert(null, encPw));

    // THEN: A UserResponse object is returned.
    assertNotNull(response);
    assertEquals("test@example.com", response.getEmail());
    assertEquals("test", response.getUsername());
    assertEquals("123", response.getId());
  }

  @Test
  void getUserByEmail_validEmail_returnsUser() {
    // GIVEN: A valid email.
    String email = "test@example.com";

    // WHEN: The getUserByEmail method is called with the valid email.
    User user = userServiceImpl.getUserByEmail(email);

    // THEN: A user object is returned.
    assertNotNull(user);
  }
}
