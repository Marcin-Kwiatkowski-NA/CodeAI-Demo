package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.exception.UserAlreadyExistsException;

/**
 * Unit tests for {@link UserService}.
 *
 * <p>All tests use a mocked {@link UserRepository}.  No production code
 * is modified and no new source files are added to {@code src/main}.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Instantiate the service with the mocked repository
        userService = new UserService(userRepository);
    }

    /* ---------- findById ---------- */

    @Test
    public void findById_success() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.findById(id);

        assertSame(user, result, "Service should return the user from the repository");
        verify(userRepository).findById(id);
    }

    @Test
    public void findById_notFound() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                     () -> userService.findById(id),
                     "Should throw UserNotFoundException when user is missing");

        verify(userRepository).findById(id);
    }

    /* ---------- findByEmail ---------- */

    @Test
    public void findByEmail_success() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = userService.findByEmail(email);

        assertSame(user, result, "Service should return the user from the repository");
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void findByEmail_notFound() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                     () -> userService.findByEmail(email),
                     "Should throw UserNotFoundException when user is missing");

        verify(userRepository).findByEmail(email);
    }

    /* ---------- createUser ---------- */

    @Test
    public void createUser_success() {
        String username = "john";
        String password = "pass";
        String email = "john@example.com";

        // Repository reports no existing user
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Repository saves the new user and returns it
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(username, password, email);

        assertSame(user, result, "Service should return the saved user");
        verify(userRepository).findByEmail(email);
        verify(userRepository).save(user);
    }

    @Test
    public void createUser_userAlreadyExists() {
        String username = "john";
        String password = "pass";
        String email = "john@example.com";

        // Repository reports a user with the same email already exists
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class,
                     () -> userService.createUser(username, password, email),
                     "Should throw UserAlreadyExistsException when email is taken");

        verify(userRepository).findByEmail(email);
    }
}
