package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
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
    private AuthServiceImpl authServiceImpl;

    private User mockUser;
    private Credential mockCredential;
    private Credential mockRefreshCredential;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setId("123");
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("hashedPassword");

        mockCredential = new Credential("token123", "Bearer", null, false);
        mockRefreshCredential = new Credential("refreshToken123", "Bearer", null, true);
    }

    @Test
    void login_withValidEmailAndPassword_shouldReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword("password", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt("123", "test@example.com", false)).thenReturn(mockCredential);
        when(authComponent.generateJwt("123", "test@example.com", true)).thenReturn(mockRefreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "password");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken123");
        assertThat(response.getTokenType()).isEqualTo("Bearer");
    }

    @Test
    void login_withInvalidEmail_shouldThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("invalid@example.com")).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("invalid@example.com", "password"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withInvalidPassword_shouldThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(mockUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("test@example.com", "wrongPassword"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withValidRefreshToken_shouldReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);

        when(authComponent.decodeJwt("refreshToken123")).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("test@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);

        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(mockUser);
        when(authComponent.generateJwt("123", "test@example.com", false)).thenReturn(mockCredential);
        when(authComponent.generateJwt("123", "test@example.com", true)).thenReturn(mockRefreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshToken123");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken123");
    }

    @Test
    void login_withInvalidRefreshToken_shouldThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);

        when(authComponent.decodeJwt("invalidToken")).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("test@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);

        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("invalidToken"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }

    @Test
    void login_withNonExistingUserInRefreshToken_shouldThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);

        when(authComponent.decodeJwt("refreshToken123")).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("nonexistent@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);

        when(userPersistentRepository.findByEmail(anyString())).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("refreshToken123"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }
}
