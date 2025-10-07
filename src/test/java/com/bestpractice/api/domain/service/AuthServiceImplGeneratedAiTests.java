package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
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
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "user@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encrypted");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "encrypted")).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshValue", "Bearer", new Date(), true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshValue", response.getRefreshToken());
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
        String email = "user@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encrypted");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "encrypted")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "user@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> true);
        User user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");
        when(userPersistentRepository.findByEmail("user@example.com")).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshValue", "Bearer", new Date(), true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshValue", response.getRefreshToken());
    }