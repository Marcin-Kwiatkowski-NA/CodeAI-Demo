package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import static org.mockito.Mockito.*;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.common.exception.TokenExpiredException;

@Test
class AuthServiceImplGeneratedAiTests {

    private AuthServiceImpl authService;
    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        BCryptPasswordEncryptionComponent encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        AuthComponent authComponent = new AuthComponent(new CredentialProperty());
        UserPersistentRepository userPersistentRepository = mock(UserPersistentRepository.class);
        this.authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void login_validCredentials() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);

        // WHEN
        AuthResponse response = authService.login("test@example.com", "password123");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
        assertEquals(0, response.getExpiresAt().getTime());
    }

    @Test
    void login_invalidCredentials() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "wrongPassword");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);

        // WHEN
        Exception exception = assertThrows(UnAuthorized.class, () -> {
            authService.login("test@example.com", "wrongPassword");
        });

        // THEN
        assertEquals("Email or password is invalid", exception.getMessage());
    }

    @Test
    void login_tokenDecode_validToken() {
        // GIVEN
        DecodedJWT decodedJWT = new DecodedJWT();
        decodedJWT.putClaim(AuthComponent.ClaimUserEmailKey, "test@example.com");
        when(authComponent.decodeJwt("validToken")).thenReturn(decodedJWT);

        // WHEN
        AuthResponse response = authService.login("validToken");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
        assertEquals(0, response.getExpiresAt().getTime());
    }

    @Test
    void login_tokenDecode_invalidToken() {
        // GIVEN
        DecodedJWT decodedJWT = new DecodedJWT();
        when(authComponent.decodeJwt("invalidToken")).thenThrow(new InternalServerError("Unknown signature secret key"));

        // WHEN
        Exception exception = assertThrows(InternalServerError.class, () -> {
            authService.login("invalidToken");
        });

        // THEN
        assertEquals("Unknown signature secret key", exception.getMessage());
    }

    @Test
    void login_tokenDecode_expiredToken()java
        // GIVEN
        DecodedJWT decodedJWT = new DecodedJWT();
        when(authComponent.decodeJwt("expiredToken")).thenThrow(new TokenExpiredException("Token is expired time"));

        // WHEN
        Exception exception = assertThrows(TokenExpiredException.class, () -> {
            authService.login("expiredToken");
        });

        // THEN
        assertEquals("Token is expired time", exception.getMessage());
    }

    @Test
    void login_refreshTokenGenerate_validUser() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password123");
        Credential token = new Credential();
        token.setTokenType("Bearer");
        token.setToken("token123");
        token.setExpiresAt(new Date());
        Credential refreshToken = new Credential();
        refreshToken.setTokenType("Bearer");
        refreshToken.setToken("refresh_token_value");
        refreshToken.setExpiresAt(new Date());

        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login("refresh_token_value");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertNotNull(response.getToken());
        assertNotNull(response.getRefreshToken());
        assertEquals(0, response.getExpiresAt().getTime());
    }

    @Test
    void login_refreshTokenGenerate_invalidUser() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(null);

        // WHEN
        Exception exception = assertThrows(UnAuthorized.class, () -> {
            authService.login("refresh_token_value");
        });

        // THEN
        assertEquals("Token invalid", exception.getMessage());
    }
}
