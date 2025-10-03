package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AuthServiceImplGeneratedAiTests.class)
public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        encryptionComponent = new BCryptPasswordEncryptionComponent();
        authComponent = new AuthComponent(new CredentialProperty());
        userPersistentRepository = new UserPersistentRepository();
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void login_validCredentials_returnsAuthResponse() {
        // GIVEN: A valid user exists in the database.
        User user = new User("1", "testUser", "test@example.com", "password123");
        userPersistentRepository.insert(user);

        // WHEN: The user attempts to log in with valid credentials.
        AuthResponse authResponse = authService.login("test@example.com", "password123");

        // THEN: An AuthResponse object is returned with the correct token and expiration time.
        assertNotNull(authResponse);
        assertEquals("Bearer", authResponse.getType());
        assertEquals("token123", authResponse. getToken());
        assertEquals("token123", authResponse.refreshToken);
        assertEquals("2024-01-01T00:00:00Z", authResponse.getExp());
    }

    @Test
    void login_invalidCredentials_throwsUnAuthorizedException() {
        // GIVEN: A valid user exists in the database.
        User user = new User("1", "testUser", "test@example.com", "password123");
        userPersistentRepository.insert(user);

        // WHEN: The user attempts to log in with invalid credentials.
        // THEN: An UnAuthorized exception is thrown.
        assertThrows(UnAuthorized.class, () -> authService.login("test@example.com", "wrongPassword"));
    }

    @Test
    void login_userNotFound_throwsUnAuthorizedException() {
        // GIVEN: A user does not exist in the database.
        // WHEN: The user attempts to log in with valid credentials.
        // THEN: An UnAuthorized exception is thrown.
        assertThrows(UnAuthorized.class, () -> authService.login("nonexistent@example.com", "password123"));
    }

    @Test
    void login_refresh_token_returnsAuthResponse() {
        // GIVEN: A valid user exists in the database.
        User user = new User("1", "testUser", "test@example.com", "password123");
        userPersistentRepository.insert(user);

        // WHEN: The user attempts to log in with a refresh token.
        // THEN: An AuthResponse object is returned with the correct token and expiration time.
        AuthResponse authResponse = authService.login("token123");

        assertNotNull(authResponse);
        assertEquals("Bearer", authResponse.getType());
        assertEquals("token123", authResponse. getToken());
        assertEquals("token123", authResponse.refreshToken);
        assertEquals("2024-01-01T00:00:00Z", authResponse.getExp());
    }
}
