package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
    void getUserById_shouldReturnUser_whenUserExists() {
        // GIVEN
        String userId = "123";
        User mockUser = new User(userId, "testUser", "test@example.com", "password");
        Mockito.when(userRepository.findById(userId)).thenReturn(mockUser);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userId);
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
    }

    @Test
    void getUserById_shouldReturnNull_whenUserDoesNotExist() {
        // GIVEN
        String userId = "123";
        Mockito.when(userRepository.findById(userId)).thenReturn(null);

        // WHEN
        User result = userService.getUserById(userId);

        // THEN
        assertThat(result).isNull();
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
    }

    @Test
    void getAuthenticatedUser_shouldReturnUser_whenCredentialsAreValid() {
        // GIVEN
        String email = "test@example.com";
        String rawPassword = "password";
        User mockUser = new User("123", "testUser", email, "encodedPassword");
        Mockito.when(userRepository.findByEmail(email)).thenReturn(mockUser);
        Mockito.when(encryptionComponent.matchedPassword("encodedPassword", rawPassword)).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser(email, rawPassword);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);
        Mockito.verify(encryptionComponent, Mockito.times(1)).matchedPassword("encodedPassword", rawPassword);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenUserNotFound() {
        // GIVEN
        String email = "test@example.com";
        String rawPassword = "password";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);
        Mockito.verifyNoInteractions(encryptionComponent);
    }

    @Test
    void getAuthenticatedUser_shouldThrowUnAuthorized_whenPasswordDoesNotMatch() {
        // GIVEN
        String email = "test@example.com";
        String rawPassword = "password";
        User mockUser = new User("123", "testUser", email, "encodedPassword");
        Mockito.when(userRepository.findByEmail(email)).thenReturn(mockUser);
        Mockito.when(encryptionComponent.matchedPassword("encodedPassword", rawPassword)).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> userService.getAuthenticatedUser(email, rawPassword))
                .isInstanceOf(UnAuthorized.class);
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);
        Mockito.verify(encryptionComponent, Mockito.times(1)).matchedPassword("encodedPassword", rawPassword);
    }

    @Test
    void generateUser_shouldReturnUserResponse_whenUserIsCreatedSuccessfully() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testUser");
        request.setEmail("test@example.com");
        request.setPassword("password");

        String encodedPassword = "encodedPassword";
        String userId = "123";
        User mockUser = new User(userId, "testUser", "test@example.com", encodedPassword);

        Mockito.when(encryptionComponent.encodePassword(request.getPassword())).thenReturn(encodedPassword);
        Mockito.when(userRepository.newId()).thenReturn(userId);
        Mockito.when(userRepository.insert(Mockito.any(User.class))).thenReturn(mockUser);

        // WHEN
        UserResponse result = userService.generateUser(request);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getUsername()).isEqualTo("testUser");
        assertThat(result.getEmail()).isEqualTo("test@example.com");
        Mockito.verify(encryptionComponent, Mockito.times(1)).encodePassword(request.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).newId();
        Mockito.verify(userRepository, Mockito.times(1)).insert(Mockito.any(User.class));
    }

    @Test
    void generateUser_shouldThrowConflict_whenUserAlreadyExists() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testUser");
        request.setEmail("test@example.com");
        request.setPassword("password");

        String encodedPassword = "encodedPassword";
        String userId = "123";

        Mockito.when(encryptionComponent.encodePassword(request.getPassword())).thenReturn(encodedPassword);
        Mockito.when(userRepository.newId()).thenReturn(userId);
        Mockito.when(userRepository.insert(Mockito.any(User.class))).thenThrow(new Conflict());

        // WHEN & THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(Conflict.class);
        Mockito.verify(encryptionComponent, Mockito.times(1)).encodePassword(request.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).newId();
        Mockito.verify(userRepository, Mockito.times(1)).insert(Mockito.any(User.class));
    }

    @Test
    void generateUser_shouldThrowInternalServerError_whenUnexpectedErrorOccurs() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("testUser");
        request.setEmail("test@example.com");
        request.setPassword("password");

        String encodedPassword = "encodedPassword";
        String userId = "123";

        Mockito.when(encryptionComponent.encodePassword(request.getPassword())).thenReturn(encodedPassword);
        Mockito.when(userRepository.newId()).thenReturn(userId);
        Mockito.when(userRepository.insert(Mockito.any(User.class))).thenThrow(new RuntimeException("Unexpected error"));

        // WHEN & THEN
        assertThatThrownBy(() -> userService.generateUser(request))
                .isInstanceOf(InternalServerError.class);
        Mockito.verify(encryptionComponent, Mockito.times(1)).encodePassword(request.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).newId();
        Mockito.verify(userRepository, Mockito.times(1)).insert(Mockito.any(User.class));
    }
}
