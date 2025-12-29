package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastructure.entity.User;
import com.bestpractice.api.infrastructure.persistent.UserPersistentRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;

    @BeforeAll
    void setUp() {
        user = new User();
        user.setId("1");
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");

        userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");

        userResponse = new UserResponse("1", "testUser", "test@example.com");
    }

    @Test
    void getUserById_ShouldReturnUser() {
        // GIVEN
        when(userRepository.findById("1")).thenReturn(user);

        // WHEN
        User result = userService.getUserById("1");

        // THEN
        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(userRepository, times(1)).findById("1");
    }

    @Test
    void getAuthenticatedUser_ShouldReturnUser_WhenCredentialsAreValid() {
        // GIVEN
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPassword", "password")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("test@example.com", "password");

        // THEN
        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(userRepository, times(1)).findByEmail("test@example.com");
        verify(encryptionComponent, times(1)).matchedPassword("encodedPassword", "password");
    }

    @Test
    void getAuthenticatedUser_ShouldThrowUnAuthorized_WhenCredentialsAreInvalid() {
        // GIVEN
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPassword", "wrongPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPassword"));
        verify(userRepository, times(1)).findByEmail("test@example.com");
        verify(encryptionComponent, times(1)).matchedPassword("encodedPassword", "wrongPassword");
    }

    @Test
    void generateUser_ShouldReturnUserResponse_WhenUserIsCreatedSuccessfully() {
        // GIVEN
        when(encryptionComponent.encodePassword("password")).thenReturn("encodedPassword");
        when(userRepository.newId()).thenReturn("1");
        when(userRepository.insert(any(User.class))).thenReturn(user);

        // WHEN
        UserResponse result = userService.generateUser(userRequest);

        // THEN
        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("testUser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        verify(encryptionComponent, times(1)).encodePassword("password");
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }

    @Test
    void generateUser_ShouldThrowConflict_WhenUserAlreadyExists() {
        // GIVEN
        when(encryptionComponent.encodePassword("password")).thenReturn("encodedPassword");
        when(userRepository.newId()).thenReturn("1");
        when(userRepository.insert(any(User.class))).thenThrow(new Conflict("User already exists"));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(userRequest));
        verify(encryptionComponent, times(1)).encodePassword("password");
        verify(userRepository, times(1)).newId();
        verify(userRepository, times(1)).insert(any(User.class));
    }
}
