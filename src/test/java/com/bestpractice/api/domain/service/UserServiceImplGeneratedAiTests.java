package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
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

@ExtendWith(MockitoExtension.class)
class UserServiceImplGeneratedAiTests {

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
    void givenValidUserId_whenGetUserById_thenReturnUser() {
        // GIVEN
        String userId = "123";
        User mockUser = new User(userId, "testUser", "test@example.com", "encryptedPassword");
        when(userRepository.findById(userId)).thenReturn(mockUser);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getUsername()).isEqualTo("testUser");
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void givenInvalidUserId_whenGetUserById_thenReturnNull() {
        // GIVEN
        String userId = "invalidId";
        when(userRepository.findById(userId)).thenReturn(null);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isNull();
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void givenValidCredentials_whenGetAuthenticatedUser_thenReturnUser() {
        // GIVEN
        String email = "test@example.com";
        String rawPassword = "password";
        User mockUser = new User("123", "testUser", email, "encryptedPassword");
        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword(rawPassword, "encryptedPassword")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser(email, rawPassword);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(rawPassword, "encryptedPassword");
    }

    @Test
    void givenInvalidEmail_whenGetAuthenticatedUser_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String rawPassword = "password";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository, times(1)).findByEmail(email);
        verifyNoInteractions(encryptionComponent);
    }

    @Test
    void givenInvalidPassword_whenGetAuthenticatedUser_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String rawPassword = "wrongPassword";
        User mockUser = new User("123", "testUser", email, "encryptedPassword");
        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword(rawPassword, "encryptedPassword")).thenReturn(false);

        // WHEN THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(rawPassword, "encryptedPassword");
    }

    @Test
    void givenValidUserRequest_whenGenerateUser_thenReturnUserResponse() {
        // GIVEN
        UserRequest request = new UserRequest("testUser", "test@example.com", "password");
               String encryptedPassword = "encryptedPassword";
        String newId = "123";
        User mockUser = new User(newId, "testUser", "test@example.com", encryptedPassword);
        when(encryptionComponent.encodePassword(request.getPassword())).thenReturn(encryptedPassword);
        when(userRepository.newId()).thenReturn(newId);
        when(userRepository.insert(any(User.class))).thenReturn(mockUser);

        // WHEN
        UserResponse result = userService.generateUser(request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(newId);
        assertThat(result.getUsername()).isEqualTo("testUser");
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        verify(encryptionComponent, times(1)).encodePassword(request.getPassword());
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void givenDuplicateUserRequest_whenGenerateUser_thenThrowConflict() {
        // GIVEN
        UserRequest request = new UserRequest("testUser", "test@example.com", "password");
        String encryptedPassword = "encryptedPassword";
        String newId = "123";
        when(encryptionComponent.encodePassword(request.getPassword())).thenReturn(encryptedPassword);
        when(userRepository.newId()).thenReturn(newId);
        when(userRepository.insert(any(User.class))).thenThrow(new Conflict());

        // WHEN THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(Conflict.class);
        verify(encryptionComponent, times(1)).encodePassword(request.getPassword());
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void givenUnexpectedError_whenGenerateUser_thenThrowInternalServerError() {
        // GIVEN
        UserRequest request = new UserRequest("testUser", "test@example.com", "password");
        String encryptedPassword = "encryptedPassword";
        String newId = "123";
        when(encryptionComponent.encodePassword(request.getPassword())).thenReturn(encryptedPassword);
        when(userRepository.newId()).thenReturn(newId);
        when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("Unexpected error"));

        // WHEN THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(InternalServerError.class);
        verify(encryptionComponent, times(1)).encodePassword(request.getPassword());
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }
}