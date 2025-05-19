package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@Test
public class AuthServiceImplGeneratedAiTests {

    @Autowired
    private AuthServiceImpl authService;

    @MockBean
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @MockBean
    private AuthComponent authComponent;

    @MockBean
    private UserPersistentRepository userPersistentRepository;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void successfulLogin() {
        // GIVEN
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        Credential expectedToken = new Credential();
        expectedToken.setTokenType("Bearer");
        expectedToken.setToken("testToken");
        Credential refreshToken = new Credential();
        refreshToken.setTokenType("Bearer");
        refreshToken.setToken("refresh");

        // WHEN
        AuthResponse response = authService.login("test@example.com", "password123");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("testToken", response.getToken());
        assertEquals("Bearer", response.getTokenType());
        assertEquals("refresh", response.getRefreshToken());
    }

    @Test
    void loginWithInvalidCredentials() {
        // GIVEN
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("wrongpassword");

        // WHEN
        // THEN
        assertThrows(() -> authService.login("test@example.com", "wrongpassword"), UnAuthorized.class, "Email or password is invalid");
    }

    @Test
    void loginWithInvalidToken() {
        // GIVEN
        // WHEN
        // THEN
        assertThrows(() -> authService.login("invalidToken"), UnAuthorized.class, "Invalid token");
    }
}
