package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplGeneratedAiTests {

    private UserPersistentRepository userRepository;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserPersistentRepository.class);
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        userService = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    public void testGetUserByIdReturnsUser() {
        // GIVEN
        User expectedUser = new User("1", "testuser", "test@example.com", "encPw");
        when(userRepository.findById("1")).thenReturn(expectedUser);

        // WHEN
        User actualUser = userService.getUserById("1");

        // THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testGetAuthenticatedUserSuccess() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encPw", "rawPw")).thenReturn(true);

        // WHEN
        User result = userService.getAuthenticatedUser("test@example.com", "rawPw");

        // THEN
        assertEquals(user, result);
    }

    @Test
    public void testGetAuthenticatedUserThrowsUnAuthorizedWhenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("missing@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("missing@example.com", "rawPw"));
    }

    @Test
    public void testGetAuthenticatedUserThrowsUnAuthorizedWhenPasswordMismatch() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "encPw");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("encPw", "rawPw")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> userService.getAuthenticatedUser("test@example.com", "rawPw"));
    }

    @Test
    public void testGenerateUserSuccess() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encPw");
        when(userRepository.newId()).thenReturn("1");
        User user = new User("1", "username", "email@example.com", "encPw");
        when(request.convert("1", "encPw")).thenReturn(user);
        when(userRepository.insert(user)).thenReturn(user);

        // WHEN
        UserResponse response = userService.generateUser(request);

        // THEN
        assertEquals("1", response.getId());
        assertEquals("username", response.getUsername());
        assertEquals("email@example.com", response.getEmail());
    }

    @Test
    public void testGenerateUserThrowsConflict() {
        // GIVEN
        UserRequest request = mock(UserRequest.class);
        when(request.getPassword()).thenReturn("rawPw");
        when(encryptionComponent.encodePassword("rawPw")).thenReturn("encPw");
        when(userRepository.newId()).thenReturn("1");
        User user = new User("1", "username", "email@example.com", "encPw");
        when(request.convert("1", "encPw")).thenReturn(user);
        when(userRepository.insert(user)).thenThrow(new Conflict());

        // WHEN & THEN
        assertThrows(Conflict.class, () -> userService.generateUser(request));
    }
}