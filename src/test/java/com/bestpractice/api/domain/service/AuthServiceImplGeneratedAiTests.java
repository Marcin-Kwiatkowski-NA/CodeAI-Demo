package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

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
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;

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
        mockUser.setId("user123");
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("encryptedPassword");

        mockCredential = new Credential("token123", "Bearer", new Date(), false);
        mockRefreshCredential = new Credential("refreshToken123", "Bearer", new Date(), true);
    }

    @Test
    void login_withValidEmailAndPassword_shouldReturnAuthResponse() {
        // GIVEN
        Mockito.when(userPersistentRepository.findByEmail(eq("test@example.com"))).thenReturn(mockUser);
        Mockito.when(encryptionComponent.matchedPassword(eq("password"), eq("encryptedPassword"))).thenReturn(true);
        Mockito.when(authComponent.generateJwt(eq("user123"), eq("test@example.com"), eq(false))).thenReturn(mockCredential);
        Mockito.when(authComponent.generateJwt(eq("user123"), eq("test@example.com"), eq(true))).thenReturn(mockRefreshCredential);

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
        Mockito.when(userPersistentRepository.findByEmail(eq("invalid@example.com"))).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("invalid@example.com", "password"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withInvalidPassword_shouldThrowUnAuthorized() {
        // GIVEN
        Mockito.when(userPersistentRepository.findByEmail(eq("test@example.com"))).thenReturn(mockUser);
        Mockito.when(encryptionComponent.matchedPassword(eq("wrongPassword"), eq("encryptedPassword"))).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("test@example.com", "wrongPassword"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withValidRefreshToken_shouldReturnNewAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);

        Mockito.when(authComponent.decodeJwt(eq("refreshToken123"))).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(emailClaim.asString()).thenReturn("test@example.com");
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);

        Mockito.when(userPersistentRepository.findByEmail(eq("test@example.com"))).thenReturn(mockUser);
        Mockito.when(authComponent.generateJwt(eq("user123"), eq("test@example.com"), eq(false))).thenReturn(mockCredential);
        Mockito.when(authComponent.generateJwt(eq("user123"), eq("test@example.com"), eq(true))).thenReturn(mockRefreshCredential);

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

        Mockito.when(authComponent.decodeJwt(eq("invalidToken"))).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(emailClaim.asString()).thenReturn("test@example.com");
        Mockito.when(refreshClaim.asBoolean()).thenReturn(false);
        Mockito.when(userPersistentRepository.findByEmail(eq("test@example.com"))).thenReturn(mockUser);

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

        Mockito.when(authComponent.decodeJwt(eq("refreshToken123"))).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(emailClaim.asString()).thenReturn("nonexistent@example.com");
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);
        Mockito.when(userPersistentRepository.findByEmail(eq("nonexistent@example.com"))).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("refreshToken123"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }
}
