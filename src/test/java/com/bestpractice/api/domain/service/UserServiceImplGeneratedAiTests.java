package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastructure.entity.User;
import com.bestpractice.api.infrastructure.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @InjectMocks
    private UserServiceImpl userService;

    private User mockUser;
    private UserRequest mockRequest;

    @BeforeEach
    void setUp() {
        mockUser = new User("1", "testUser", "test@example.com", "encryptedPassword");
        mockRequest = new UserRequest();
        mockRequest.setUsername("testUser");
        mockRequest.setEmail("test@example.com");
        mockRequest.setPassword("rawPassword");
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        String userId = "1";
        when(userRepository.findById(userId)).thenReturn(mockUser);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(mockUser.getId());
        assertThat(result.getUsername()).isEqualTo(mockUser.getUsername());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        String email = "test@example.com";
        String rawPassword = "rawPassword";
        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword(mockUser.getPassword(), rawPassword)).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser(email, rawPassword);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(mockUser.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(mockUser.getPassword(), rawPassword);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        String email = "nonexistent@example.com";
        String rawPassword = "rawPassword";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository, times(1)).findByEmail(email);
        verifyNoInteractions(encryptionComponent);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        String email = "test@example.com";
        String rawPassword = "wrongPassword";
        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword(mockUser.getPassword(), rawPassword)).thenReturn(false);

        // WHEN THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        verify(userRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(mockUser.getPassword(), rawPassword);
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenUserIsCreatedSuccessfully() {
        // GIVEN
        String encodedPassword = "encodedPassword";
        when(encryptionComponent.encodePassword(mockRequest.getPassword())).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn("1");
        when(userRepository.insert(any(User.class))).thenReturn(mockUser);

        // WHEN
        UserResponse result = userService.generateUser(mockRequest);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(mockUser.getId());
        assertThat(result.getUsername()).isEqualTo(mockUser.getUsername());
        assertThat(result.getEmail()).isEqualTo(mockUser.getEmail());
        verify(encryptionComponent, times(1)).encodePassword(mockRequest.getPassword());
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUser_shouldThrowConflict_whenUserAlreadyExists() {
        // GIVEN
        String encodedPassword = "encodedPassword";
        when(encryptionComponent.encodePassword(mockRequest.getPassword())).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn("1");
        doThrow(new Conflict("User already exists")).when(userRepository).insert(any(User.class));

        // WHEN THEN
        assertThatThrownBy(() -> userService.generateUser(mockRequest))
                .isInstanceOf(Conflict.class)
                .hasMessageContaining("User already exists");
        verify(encryptionComponent, times(1)).encodePassword(mockRequest.getPassword());
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenUnexpectedErrorOccurs() {
        // GIVEN
        String encodedPassword = "encodedPassword";
        when(encryptionComponent.encodePassword(mockRequest.getPassword())).thenReturn(encodedPassword);
        when(userRepository.newId()).thenReturn("1");
        doThrow(new RuntimeException("Unexpected error")).when(userRepository).insert(any(User.class));

        // WHEN THEN
        assertThatThrownBy(() -> userService.generateUser(mockRequest))
                .isInstanceOf(InternalServerError.class)
                .hasCauseInstanceOf(RuntimeException.class)
                .hasMessageContaining("Unexpected error");
        verify(encryptionComponent, times(1)).encodePassword(mockRequest.getPassword());
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }
}
