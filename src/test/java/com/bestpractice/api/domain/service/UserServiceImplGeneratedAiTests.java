package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserPersistentRepository.class);
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
        reset(userRepository, encryptionComponent);
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        String userId = "123";
        User expectedUser = new User(userId, "john", "john@example.com", "encodedPw");
        when(userRepository.findById(userId)).thenReturn(expectedUser);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userId);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        String email = "john@example.com";
        String rawPw = "password";
        User user = new User("1", "john", email, "encodedPw");
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", rawPw)).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser(email, rawPw);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword("encodedPw", rawPw);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        String email = "unknown@example.com";
        String rawPw = "password";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPw))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        String email = "john@example.com";
        String rawPw = "wrongPw";
        User user = new User("1", "john", email, "encodedPw");
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", rawPw)).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPw))
                .isInstanceOf(UnAuthorized.class);
        verify(encryptionComponent, times(1)).matchedPassword("encodedPw", rawPw);
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenInsertSucceeds() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("password");
        String newId = "123";
        String encodedPw = "encodedPw";
        User userToInsert = new User(newId, "john", "john@example.com", encodedPw);
        when(userRepository.newId()).thenReturn(newId);
        when(encryptionComponent.encodePassword("password")).thenReturn(encodedPw);
        when(userRepository.insert(any(User.class))).thenReturn(userToInsert);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(newId);
        assertThat(response.getEmail()).isEqualTo("john@example.com");
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUser_shouldThrowConflict_whenRepositoryThrowsConflict() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("password");
        when(userRepository.newId()).thenReturn("123");
        when(encryptionComponent.encodePassword("password")).thenReturn("encodedPw");
        when(userRepository.insert(any(User.class))).thenThrow(new Conflict());

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(Conflict.class);
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenRepositoryThrowsGenericException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("password");
        when(userRepository.newId()).thenReturn("123");
        when(encryptionComponent.encodePassword("password")).thenReturn("encodedPw");
        when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN / THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(InternalServerError.class);
        verify(userRepository, times(1)).insert(any(User.class));
    }
}
