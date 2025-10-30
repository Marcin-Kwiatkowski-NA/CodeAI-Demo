package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void givenValidCredentials_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User("1", "username", email, "encryptedPassword");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshValue", "Bearer", new Date(), true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User("1", "username", email, "encryptedPassword");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> true);
        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        User user = new User("1", "username", email, "encryptedPassword");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshValue", "Bearer", new Date(), true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
    }