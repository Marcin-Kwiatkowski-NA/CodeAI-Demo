package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import com.auth0.jwt.interfaces.Claim;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private UserPersistentRepository userPersistentRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    private User user;
    private Credential token;
    private Credential refreshToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId("1");
        user.setEmail("test@example.com");
        user.setPassword("hashedPassword");

        token = new Credential("accessToken", "Bearer", new Date(), false);
        refreshToken = new Credential("refreshToken", "Bearer", new Date(), true);
    }

    @Test
    void login_withValidCredentials_returnsAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("password", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(eq("1"), eq("test@example.com"), eq(false))).thenReturn(token);
        when(authComponent.generateJwt(eq("1"), eq("test@example.com"), eq(true))).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login("test@example.com", "password");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("accessToken");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken");
        assertThat(response.getTokenType()).isEqualTo("Bearer");
    }

    @Test
    void login_withInvalidEmail_throwsUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("invalid@example.com")).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login("invalid@example.com", "password"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withInvalidPassword_throwsUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login("test@example.com", "wrongPassword"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withValidRefreshToken_returnsAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);

        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("test@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt("refreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        when(authComponent.generateJwt(eq("1"), eq("test@example.com"), eq(false))).thenReturn(token);
        when(authComponent.generateJwt(eq("1"), eq("test@example.com"), eq(true))).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login("refreshToken");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("accessToken");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    void login_withInvalidRefreshToken_throwsUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);

        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("test@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);

        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login("invalidRefreshToken"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }

    @Test
    void login_withRefreshTokenAndUserNotFound_throwsUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);

        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("notfound@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt("refreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("notfound@example.com")).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login("refreshToken"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }
}
