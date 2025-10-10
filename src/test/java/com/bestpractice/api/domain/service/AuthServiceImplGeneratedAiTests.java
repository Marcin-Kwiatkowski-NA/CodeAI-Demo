package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

@ExtendWith(AuthServiceImplGeneratedAiTests.class)
class AuthServiceImplGeneratedAiTests {

    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        BCryptPasswordEncryptionComponent encryptionComponent = new BCryptPasswordEncryptionComponent();
        AuthComponent authComponent = new AuthComponent(new CredentialProperty());
        UserPersistentRepository userPersistentRepository = new UserPersistentRepository();
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void loginSuccessful() {
        // GIVEN a user with email "test@example.com" and password "password123"
        User user = new User("1", "testUser", "test@example.com", "password123");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);

        // WHEN the user attempts to log in with email "test@example.com" and password "password123"
        AuthResponse response = authService.login("test@example.com", "password123");

        // THEN the response should contain the generated token, token type, expiration time, and refresh token
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
        assertNotNull(response.getExp());
    }

    @Test
    void loginUnsuccessfulInvalidPassword() {
        // GIVEN a user with email "test@example.com" and password "password123"
        User user = new User("1", "testUser", "test@example.com", "password123");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);

        // WHEN the user attempts to log in with email "test@example.com" and an invalid password
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            authService.login("test@example.com", "wrongPassword");
        });

        // THEN an UnAuthorized exception should be thrown
        assertEquals("Email or password is invalid", exception.getMessage());
    }

    @Test
    void loginUnsuccessfulInvalidEmail() {
        // GIVEN no user with email "nonexistent@example.com"
        when(userPersistentRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // WHEN the user attempts to log in with an invalid email
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            authService.login("nonexistent@example.com", "password123");
        });

        // THEN an UnAuthorized exception should be thrown
        assertEquals("Email or password is invalid", exception.getMessage());
    }
}
