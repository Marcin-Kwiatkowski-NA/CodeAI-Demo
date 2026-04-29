package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplGeneratedAiTests {

  @Mock
  private UserPersistentRepository userRepository;

  @Mock
  private BCryptPasswordEncryptionComponent encryptionComponent;

  @InjectMocks
  private UserServiceImpl userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getUserById_shouldReturnUser_whenUserExists() {
    // GIVEN
    User expectedUser = new User();
    when(userRepository.findById("123")).thenReturn(expectedUser);

    // WHEN
    User result = userService.getUserById("123");

    // THEN
    assertThat(result).isEqualTo(expectedUser);
    verify(userRepository).findById("123");
  }

  @Test
  void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
    // GIVEN
    User user = new User();
    user.setPassword("encryptedPw");
    when(userRepository.findByEmail("test@example.com")).thenReturn(user);
    when(encryptionComponent.matchedPassword("rawPw", "encryptedPw")).thenReturn(true);

    // WHEN
    User result = userService.getAuthenticatedUser("test@example.com", "rawPw");

    // THEN
    assertThat(result).isEqualTo(user);
    verify(userRepository).findByEmail("test@example.com");
    verify(encryptionComponent).matchedPassword("rawPw", "encryptedPw");
  }

  @Test
  void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
    // GIVEN
    when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

    // WHEN / THEN
    assertThatThrownBy(() -> userService.getAuthenticatedUser("missing@example.com", "pw"))
        .isInstanceOf(UnAuthorized.class);
  }

  @Test
  void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
    // GIVEN
    User user = new User();
    user.setPassword("encryptedPw");
    when(userRepository.findByEmail("test@example.com")).thenReturn(user);
    when(encryptionComponent.matchedPassword("wrongPw", "encryptedPw")).thenReturn(false);

    // WHEN / THEN
    assertThatThrownBy(() -> userService.getAuthenticatedUser("test@example.com", "wrongPw"))
        .isInstanceOf(UnAuthorized.class);
  }

  @Test
  void generateUser_shouldReturnUserResponse_whenUserCreatedSuccessfully() {
    // GIVEN
    UserRequest request = mock(UserRequest.class);
    User user = new User();
    user.setId("id123");
    user.setUsername("username");
    user.setEmail("email@example.com");
    when(encryptionComponent.encodePassword(anyString())).thenReturn("encodedPw");
    when(userRepository.newId()).thenReturn("id123");
    when(request.convert("id123", "encodedPw")).thenReturn(user);
    when(userRepository.insert(user)).thenReturn(user);

    // WHEN
    UserResponse response = userService.generateUser(request);

    // THEN
    assertThat(response.getId()).isEqualTo("id123");
    assertThat(response.getUsername()).isEqualTo("username");
    assertThat(response.getEmail()).isEqualTo("email@example.com");
    verify(userRepository).insert(user);
  }

  @Test
  void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
    // GIVEN
    UserRequest request = mock(UserRequest.class);
    User user = new User();
    when(encryptionComponent.encodePassword(anyString())).thenReturn("encodedPw");
    when(userRepository.newId()).thenReturn("id123");
    when(request.convert("id123", "encodedPw")).thenReturn(user);
    when(userRepository.insert(user)).thenThrow(new Conflict());

    // WHEN / THEN
    assertThatThrownBy(() -> userService.generateUser(request))
        .isInstanceOf(Conflict.class);
  }

  @Test
  void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsGenericException() {
    // GIVEN
    UserRequest request = mock(UserRequest.class);
    User user = new User();
    when(encryptionComponent.encodePassword(anyString())).thenReturn("encodedPw");
    when(userRepository.newId()).thenReturn("id123");
    when(request.convert("id123", "encodedPw")).thenReturn(user);
    when(userRepository.insert(user)).thenThrow(new RuntimeException("DB error"));

    // WHEN / THEN
    assertThatThrownBy(() -> userService.generateUser(request))
        .isInstanceOf(InternalServerError.class);
  }
}
