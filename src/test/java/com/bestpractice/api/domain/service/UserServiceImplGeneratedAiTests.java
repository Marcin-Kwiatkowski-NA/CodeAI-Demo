package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
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
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        reset(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        String userId = "123";
        User expectedUser = new User(userId, "john", "john@example.com", "encPw");
        when(userRepository.findById(userId)).thenReturn(expectedUser);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userId);
        verify(userRepository).findById(userId);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        String email = "john@example.com";
        String rawPw = "password";
        String encPw = "encPw";
        User user = new User("1", "john", email, encPw);
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(encPw, rawPw)).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser(email, rawPw);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        verify(userRepository).findByEmail(email);
        verify(encryptionComponent).matchedPassword(encPw, rawPw);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        String email = "notfound@example.com";
        String rawPw = "password";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPw))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository).findByEmail(email);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        String email = "john@example.com";
        String rawPw = "wrongPw";
        String encPw = "encPw";
        User user = new User("1", "john", email, encPw);
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(encPw, rawPw)).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPw))
                .isInstanceOf(UnAuthorized.class);
        verify(encryptionComponent).matchedPassword(encPw, rawPw);
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenUserIsCreatedSuccessfully() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("password");

        String newId = "123";
        String encPw = "encPw";
        User user = new User(newId, "john", "john@example.com", encPw);

        when(encryptionComponent.encodePassword("password")).thenReturn(encPw);
        when(userRepository.newId()).thenReturn(newId);
        when(userRepository.insert(any(User.class))).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(newId);
        assertThat(response.getUsername()).isEqualTo("john");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
        verify(userRepository).insert(any(User.class));
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("password");

        when(encryptionComponent.encodePassword("password")).thenReturn("encPw");
        when(userRepository.newId()).thenReturn("123");
        when(userRepository.insert(any(User.class))).thenThrow(new Conflict());

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(Conflict.class);
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsUnexpectedException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("password");

        when(encryptionComponent.encodePassword("password")).thenReturn("encPw");
        when(userRepository.newId()).thenReturn("123");
        when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(InternalServerError.class);
    }
}
