package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.springframework.stereotype.Service;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@Service
public class UserServiceImplGeneratedAiTests {

  private final UserPersistentRepository userRepository;
  private final BCryptPasswordEncryptionComponent encryptionComponent;
  private UserServiceImpl userServiceImpl;

  @BeforeEach
  void setUp(UserPersistentRepository userRepository, BCryptPasswordEncryptionComponent encryptionComponent) {
    this.userRepository = userRepository;
    this.encryptionComponent = encryptionComponent;
    this.userServiceImpl = new UserServiceImpl(userRepository, encryptionComponent);
  }

  @Test
  void getUserById_validId_returnsUser() {
    // GIVEN
    String id = "123";
    Mockito.when(userRepository.findById(id)).thenReturn(new User(id, "testUser", "test@example.com", "password"));

    // WHEN
    User user = userServiceImpl.getUserById(id);

    // THEN
    assertNotNull(user);
    assertEquals("123", user.getId());
    assertEquals("testUser", user.getUsername());
    assertEquals("test@example.com", user.getEmail());
    assertEquals("password", user.getPassword());
  }

  @Test
  void getAuthenticatedUser_validCredentials_returnsUser() {
    // GIVEN
    String email = "test@example.com";
    String rawPw = "password";
    User user = new User(email, "testUser", email, "password");
    Mockito.when(userRepository.findByEmail(email)).thenReturn(user);
    Mockito.when(encryptionComponent.matchedPassword(user.getPassword(), rawPw)).thenReturn(true);

    // WHEN
    User authenticatedUser = userServiceImpl.getAuthenticatedUser(email, rawPw);

    // THEN
    assertNotNull(authenticatedUser);
    assertEquals("testUser", authenticatedUser.getUsername());
    assertEquals("test@example.com", authenticatedUser.getEmail());
    assertEquals("password", authenticatedUser.getPassword());
  }

  @Test
  void getAuthenticatedUser_invalidCredentials_throwsUnAuthorized() {
    // GIVEN
    String email = "test@example.com";
    String rawPw = "wrongPassword";
    User user = new User(email, "testUser", email, "password");
    Mockito.when(userRepository.findByEmail(email)).thenReturn(user);
    Mockito.when(encryptionComponent.matchedPassword(user.getPassword(), rawPw)).thenReturn(false);

    // WHEN
    // THEN
    assertThrows(UnAuthorized.class, () -> userServiceImpl.getAuthenticatedUser(email, rawPw));
  }

  @Test
  void generateUser_validRequest_returnsUserResponse() {
    // GIVEN
    UserRequest request = new UserRequest("testUser", "test@example.com", "password");
    Mockito.when(userRepository.newId()).thenReturn("456");
    Mockito.when(encryptionComponent.encodePassword(request.getPassword())).thenReturn("hashedPassword");
    User user = new User("456", "testUser", "test@example.com", "password    assertEquals("456", userResponse.getId());
    assertEquals("testUser", userResponse.getUsername());
    assertEquals("test@example.com", userResponse.getEmail());
  }

  @Test
  void generateUser_invalidRequest_throwsConflict() {
    // GIVEN
    UserRequest request = new UserRequest("testUser", "test@example.com", "password");
    Mockito.when(userRepository.newId()).thenReturn("456");
    Mockito.when(encryptionComponent.encodePassword(request.getPassword())).thenReturn("hashedPassword");
    User user = new User("456", "testUser", "test@example.com", "password");
    Mockito.doThrow(new Conflict("User already exists")).when(userRepository).insert(user);

    // WHEN
    // THEN
    assertThrows(Conflict.class, () -> userServiceImpl.generateUser(request));
  }

  @Test
  void getUserByEmail_validEmail_returnsUser() {
    // GIVEN
    String email = "test@example.com";
    User user = new User(email, "testUser", email, "password");

    // WHEN
    User retrievedUser = userServiceImpl.getUserByEmail(email);

    // THEN
    assertNotNull(retrievedUser);
    assertEquals("testUser", retrievedUser.getUsername());
    assertEquals("test@example.com", retrievedUser.getEmail());
    assertEquals("password", retrievedUser.getPassword());
  }

  @Test
  void getUserByEmail_invalidEmail_throwsInternalServerError() {
    // GIVEN
    String email = "invalidEmail";

    // WHEN
    // THEN
    assertThrows(InternalServerError.class, () -> userServiceImpl.getUserByEmail(email));
  }
}
