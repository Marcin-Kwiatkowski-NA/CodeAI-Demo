package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastructure.entity.User;
import com.bestpractice.api.infrastructure.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserPersistentRepository userPersistentRepository;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    private User user;
    private DecodedJWT decodedJWT;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("1");
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");

        decodedJWT = mock(DecodedJWT.class);
    }

    @Test
    void login_ValidCredentials_ShouldReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("password", "encodedPassword")).thenReturn(true);
        when(authComponent.generateJwt(any(), any(), anyBoolean())).thenReturn(new Credential("token", "refreshToken", 36L));

        // WHEN
        AuthResponse response = authService.login("test@example.com", "password");

        // THEN
        assertNotNull(response);
        assertEquals("token", response.getToken());
        assertEquals("refreshToken", response.getRefreshToken());
        verify(userPersistentRepository, times(1)).findByEmail("test@example.com");
        verify(encryptionComponent, times(1)).matchedPassword("password", "encodedPassword");
        verify(authComponent, times(1)).generateJwt(any(), any(), anyBoolean());
    }

    @Test
    void login_InvalidCredentials_ShouldThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login("test@example.com", "wrongPassword"));
        verify(userPersistentRepository, times(1)).findByEmail("test@example.com");
        verify(encryptionComponent, never()).matchedPassword(any(), any());
        verify(authComponent, never()).generateJwt(any(), any(), anyBoolean());
    }

    @Test
    void login_InvalidPassword_ShouldThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("wrongPassword", "encodedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login("test@example.com", "wrongPassword"));
        verify(userPersistentRepository, times(1)).findByEmail("test@example.com");
        verify(encryptionComponent, times(1)).matchedPassword("wrongPassword", "encodedPassword");
        verify(authComponent, never()).generateJwt(any(), any(), anyBoolean());
    }

    @Test
    void login_RefreshToken_ShouldReturnAuthResponse() {
        // GIVEN
        when(decodedJWT.getClaim("user_id").asString()).thenReturn("1");
        when(decodedJWT.getClaim("user_email").asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim("refresh").asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        when(authComponent.generateJwt(any(), any(), anyBoolean())).thenReturn(new Credential("token", "refreshToken", 36L));

        // WHEN
        AuthResponse response = authService.login(decodedJWT);

        // THEN
        assertNotNull(response);
        assertEquals("token", response.getToken());
        assertEquals("refreshToken", response.getRefreshToken());
        verify(decodedJWT, times(1)).getClaim("user_id");
        verify(decodedJWT, times(1)).getClaim("user_email");
        verify(decodedJWT, times(1)).getClaim("refresh");
        verify(userPersistentRepository, times(1)).findByEmail("test@example.com");
        verify(authComponent, times(1)).generateJwt(any(), any(), anyBoolean());
    }
}
