package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AuthServiceImplGeneratedAiTests {

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private UserPersistentRepository userPersistentRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private DecodedJWT decodedJWT;

    @Mock
    private Claim emailClaim;

    @Mock
    private Claim refreshClaim;

    @Mock
    private com.bestpractice.api.domain.model.Credential tokenCredential;

    @Mock
    private com.bestpractice.api.domain.model.Credential refreshCredential;

    @Mock
    private User user;

    @BeforeEach
    void setUp() {
        // No additional setup required; mocks are reset automatically by MockitoExtension
    }

    @Test
    void testLoginSuccess() {
        // GIVEN
        String email = "user@example.com";
        String password = "securePassword";
        String tokenType = "Bearer";
        String token = "token123";
        String refreshToken = "refresh123";
        Date expiresAt = new Date(System.currentTimeMillis() + 3600_000L);

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn(email);
        when(encryptionComponent.matchedPassword(password, "hashedPassword")).thenReturn(true);
        when(tokenCredential.getTokenType()).thenReturn(tokenType);
        when(tokenCredential.getToken()).thenReturn(token);
        when(tokenCredential.getExp()).thenReturn(expiresAt);
        when(refreshCredential.getToken()).thenReturn(refreshToken);
        when(authComponent.generateJwt(1L, email, false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(1L, email, true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo(tokenType);
        assertThat(response.getToken()).isEqualTo(token);
        assertThat(response.getRefreshToken()).isEqualTo(refreshToken);
        assertThat(response.getExpiresAt()).isEqualTo(expiresAt);

        verify(authComponent, times(1)).generateJwt(1L, email, false);
        verify(authComponent, times(1)).generateJwt(1L, email, true);
    }

    @Test
    void testLoginUserNotFound() {
        // GIVEN
        String email = "unknown@example.com";
        String password = "anyPassword";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Email or password is invalid");
    }

    @Test
    void testLoginPasswordMismatch() {
        // GIVEN
        String email = "user@example.com";
        String password = "wrongPassword";
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(user.getId()).thenReturn(2L);
        when(user.getEmail()).thenReturn(email);
        when(encryptionComponent.matchedPassword(password, "hashedPassword")).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Email or password is invalid");
    }

    @Test
    void testLoginRefreshTokenSuccess() {
        // GIVEN
        String refreshToken = "refreshTokenValue";
        String email = "user@example.com";
        String tokenType = "Bearer";
        String token = "tokenABC";
        String newRefreshToken = "refreshABC";
        Date expiresAt = new Date(System.currentTimeMillis() + 7200_000L);

        when(decodedJWT.getClaim("email")).thenReturn(emailClaim);
        when(emailClaim.asString()).thenReturn(email);
        when(decodedJWT.getClaim("isRefresh")).thenReturn(refreshClaim);
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(user.getId()).thenReturn(3L);
        when(user.getEmail()).thenReturn(email);
        when(tokenCredential.getTokenType()).thenReturn(tokenType);
        when(tokenCredential.getToken()).thenReturn(token);
        when(tokenCredential.getExp()).thenReturn(expiresAt);
        when(refreshCredential.getToken()).thenReturn(newRefreshToken);
        when(authComponent.generateJwt(3L, email, false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(3L, email, true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authService.login(refreshToken);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo(tokenType);
        assertThat(response.getToken()).isEqualTo(token);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
        assertThat(response.getExpiresAt()).isEqualTo(expiresAt);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Boolean> flagCaptor = ArgumentCaptor.forClass(Boolean.class);

        verify(authComponent, times(2)).generateJwt(idCaptor.capture(), emailCaptor.capture(), flagCaptor.capture());
        assertThat(idCaptor.getAllValues()).containsExactly(3L, 3L);
        assertThat(emailCaptor.getAllValues()).containsExactly(email, email);
        assertThat(flagCaptor.getAllValues()).containsExactly(false, true);
    }

    @Test
    void testLoginRefreshTokenInvalidToken() {
        // GIVEN
        String refreshToken = "nonRefreshToken";
        when(decodedJWT.getClaim("email")).thenReturn(emailClaim);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(decodedJWT.getClaim("isRefresh")).thenReturn(refreshClaim);
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");
    }

    @Test
    void testLoginRefreshTokenInvalidEmail() {
        // GIVEN
        String refreshToken = "validRefreshToken";
        when(decodedJWT.getClaim("email")).thenReturn(emailClaim);
        when(emailClaim.asString()).thenReturn("nonexistent@example.com");
        when(decodedJWT.getClaim("isRefresh")).thenReturn(refreshClaim);
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");
    }

    @Test
    void testLoginRefreshTokenNotRefresh() {
        // GIVEN
        String refreshToken = "someToken";
        when(decodedJWT.getClaim("email")).thenReturn(emailClaim);
        when(emailClaim.asString()).thenReturn("user@example.com");
        when(decodedJWT.getClaim("isRefresh")).thenReturn(refreshClaim);
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");
    }
}
