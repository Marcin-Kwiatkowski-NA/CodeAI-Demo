package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

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
    void login_validCredentials() {
        // GIVEN: A valid user exists in the database.
        User user = new User("1", "testUser", "test@example.com", "password123");
        userPersistentRepository.insert(user);

        // WHEN: The user attempts to log in with valid credentials.
        AuthResponse response = authService.login("test@example.com", "password123");

        // THEN: A successful login response is returned with the generated tokens.
        assertNotNull(response);
        assertEquals("jwt", response.getTokenType());
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
        assertNotNull(response.getExp());
    }

    @Test
    void login_invalidCredentials() {
        // GIVEN: A user exists in the database.
        User user = new User("1", "testUser", "test@example.com", "wrongPassword");
        userPersistentRepository.insert(user);

        // WHEN: The user attempts to log in with invalid credentials.
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> {
            authService.login("test@example.com", "wrongPassword");
        });

        // THEN: An UnAuthorized exception is thrown.
        assertEquals("Email or password is invalid", exception.getMessage());
    }

    @Test
    void login_userNotFound() {
        // GIVEN: A user does not exist in the database.
        // WHEN: The user attempts to log in with valid credentials.
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> {
            authService.login("nonExistentUser", "password123");
        });

        // THEN: An UnAuthorized exception is thrown.
        assertEquals("Email or password is invalid", exception.getMessage());
    }

    @Test
    void login_refresh_token() {
        // GIVEN: A user exists in the database.
        User user = new User("1", "testUser", "test@example.com", "password123");
        userPersistentRepository.insert(user);

        // WHEN: The user attempts to log in with a refresh token.
        AuthResponse response = authService.login(userPersistentRepository.getRefreshToken());

        // THEN: A successful login response is returned with the generated tokens.
        assertNotNull(response);
        assertEquals("jwt", response.getTokenType());
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
        assertNotNull(response.getExp());
    }
}