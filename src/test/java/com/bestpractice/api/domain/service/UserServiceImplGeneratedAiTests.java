package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.ArgumentMatchers.any;

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

@ExtendWith(MockitoExtension.class)
class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @InjectMocks
    private UserServiceImpl userService;

    private User mockUser;
    private UserRequest mockUserRequest;

    @BeforeEach
    void setUp() {
        mockUser = new User("1", "testUser", "test@example.com", "encryptedPassword");
        mockUserRequest = new UserRequest();
        mockUserRequest.setUsername("testUser");
        mockUserRequest.setEmail("test@example.com");
        mockUserRequest.setPassword("rawPassword");
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        // GIVEN
        String userId = "1";
        when(userRepository.findById(userId)).thenReturn(mockUser);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(mockUser.getId());
        assertThat(result.getUsername()).isEqualTo(mockUser.getUsername());
        assertThat(result.getEmail()).isEqualTo(mockUser.getEmail());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getAuthenticatedUser_ShouldReturnUser_WhenCredentialsAreValid() {
        // GIVEN
        String email = "test@example.com";
        String rawPassword = "rawPassword";
        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword(mockUser.getPassword(), rawPassword)).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser(email, rawPassword);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(mockUser.getPassword(), rawPassword);
    }

    @Test
    void getAuthenticatedUser_ShouldThrowUnAuthorized_WhenUserNotFound() {
        // GIVEN
        String email = "nonexistent@example.com";
        String rawPassword = "rawPassword";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository, times(1)).findByEmail(email);
        verifyNoInteractions(encryptionComponent);
    }

    @Test
    void getAuthenticatedUser_ShouldThrowUnAuthorized_WhenPasswordDoesNotMatch() {
        // GIVEN
        String email = "test@example.com";
        String rawPassword = "wrongPassword";
        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword(mockUser.getPassword(), rawPassword)).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(mockUser.getPassword(), rawPassword);
    }

    @Test
    void generateUser_ShouldReturnUserResponse_WhenUserIsCreatedSuccessfully() {
        // GIVEN
        String encodedPassword = "encodedPassword";
        when(encryptionComponent.encodePassword(mockUserRequest.getPassword())).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn("1");
        when(userRepository.insert(any(User.class))).thenReturn(mockUser);

        // WHEN
        UserResponse result = userService.generateUser(mockUserRequest);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(mockUser.getId());
        assertThat(result.getUsername()).isEqualTo(mockUser.getUsername());
        assertThat(result.getEmail()).isEqualTo(mockUser.getEmail());
        verify(encryptionComponent, times(1)).encodePassword(mockUserRequest.getPassword());
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUser_ShouldThrowConflict_WhenUserAlreadyExists() {
        // GIVEN
        String encodedPassword = "encodedPassword";
        when(encryptionComponent.encodePassword(mockUserRequest.getPassword())).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn("1");
        when(userRepository.insert(any(User.class))).thenThrow(new Conflict());

        // WHEN & THEN
        assertThatThrownBy(() -> userService.generateUser(mockUserRequest))
                .isInstanceOf(Conflict.class);
        verify(encryptionComponent, times(1)).encodePassword(mockUserRequest.getPassword());
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUser_ShouldThrowInternalServerError_WhenUnexpectedErrorOccurs() {
        // GIVEN
        String encodedPassword = "encodedPassword";
        when(encryptionComponent.encodePassword(mockUserRequest.getPassword())).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn("1");
        when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("Unexpected error"));

        // WHEN & THEN
        assertThatThrownBy(() -> userService.generateUser(mockUserRequest))
                .isInstanceOf(InternalServerError.class);
        verify(encryptionComponent, times(1)).encodePassword(mockUserRequest.getPassword());
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }
}
