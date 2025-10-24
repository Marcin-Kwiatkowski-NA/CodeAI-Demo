package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserPersistentRepository.class);
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void testGetUserByIdReturnsUser() {
        // GIVEN
        User expectedUser = new User();
        when(userRepository.findById("123")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("123");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testGetAuthenticatedUserSuccess() {
        // GIVEN
        User user = new User();
        user.setPassword("encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "rawPw")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, result);
    }

    @Test
    void testGetAuthenticatedUserThrowsUnauthorizedWhenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("missing@example.com", "rawPw"));
    }

    @Test
    void testGetAuthenticatedUserThrowsUnauthorizedWhenPasswordMismatch() {
        // GIVEN
        User user = new User();
        user.setPassword("encodedPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encodedPw", "wrongPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "wrongPw"));
    }

    @Test
    void testGenerateUserSuccess() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        user.setId("newId");
        user.setUsername("username");
        user.setEmail("email@example.com");
        when(request.convert("newId", "encodedPw")).thenReturn(user);
        when(userRepository.insert(user)).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("newId", response.getId());
        assertEquals("username", response.getUsername());
        assertEquals("email@example.com", response.getEmail());
    }

    @Test
    void testGenerateUserThrowsConflict() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        when(request.convert("newId", "encodedPw")).thenReturn(user);
        when(userRepository.insert(user)).thenThrow(new Conflict());

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }

    @Test
    void testGenerateUserThrowsInternalServerError() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encodedPw");
        when(userRepository.newId()).thenReturn("newId");
        User user = new User();
        when(request.convert("newId", "encodedPw")).thenReturn(user);
        when(userRepository.insert(user)).thenThrow(new RuntimeException());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> userService.generateUser(request));
    }
}
