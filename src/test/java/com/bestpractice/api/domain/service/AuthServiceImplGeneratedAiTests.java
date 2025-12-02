package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import org.mockito.Mockito;
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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_withValidEmailAndPassword_shouldReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password123";
        User user = new User("1", "testUser", email, "encryptedPassword");
        Credential token = new Credential("accessToken", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshToken", "Bearer", new Date(), true);

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo(token.getTokenType());
        assertThat(response.getToken()).isEqualTo(token.getToken());
        assertThat(response.getRefreshToken()).isEqualTo(refreshToken.getToken());
        assertThat(response.getExp()).isEqualTo(token.getExp());

        verify(userPersistentRepository).findByEmail(email);
        verify(encryptionComponent).matchedPassword(password, user.getPassword());
        verify(authComponent).generateJwt(user.getId(), user.getEmail(), false);
        verify(authComponent).generateJwt(user.getId(), user.getEmail(), true);
    }

    @Test
    void login_withInvalidEmail_shouldThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password123";

        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Email or password is invalid");

        verify(userPersistentRepository).findByEmail(email);
        verifyNoInteractions(encryptionComponent, authComponent);
    }

    @Test
    void login_withInvalidPassword_shouldThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User("1", "testUser", email, "encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Email or password is invalid");

        verify(userPersistentRepository).findByEmail(email);
        verify(encryptionComponent).matchedPassword(password, user.getPassword());
        verifyNoInteractions(authComponent);
    }

    @Test
    void login_withValidRefreshToken_shouldReturnAuthResponse() {
        // GIVEN
        String refreshToken = "validRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        String email = "test@example.com";
        User user = new User("1", "testUser", email, "encryptedPassword");
        Credential token = new Credential("accessToken", "Bearer", new Date(), false);
        Credential newRefreshToken = new Credential("newRefreshToken", "Bearer", new Date(), true);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn(email);
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(newRefreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshToken);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo(token.getTokenType());
        assertThat(response.getToken()).isEqualTo(token.getToken());
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken.getToken());
        assertThat(response.getExp()).isEqualTo(token.getExp());

        verify(authComponent).decodeJwt(refreshToken);
        verify(decodedJWT).getClaim(AuthComponent.ClaimUserEmailKey);
        verify(decodedJWT).getClaim(AuthComponent.ClaimRefreshKey);
        verify(userPersistentRepository).findByEmail(email);
        verify(authComponent).generateJwt(user.getId(), user.getEmail(), false);
        verify(authComponent).generateJwt(user.getId(), user.getEmail(), true);
    }

    @Test
    void login_withInvalidRefreshToken_shouldThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        String email = "test@example.com";

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn(email);
        when(refreshClaim.asBoolean()).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");

        verify(authComponent).decodeJwt(refreshToken);
        verify(decodedJWT).getClaim(AuthComponent.ClaimUserEmailKey);
        verify(decodedJWT).getClaim(AuthComponent.ClaimRefreshKey);
        verifyNoInteractions(userPersistentRepository, encryptionComponent);
    }

    @Test
    void login_withRefreshTokenForNonExistentUser_shouldThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "validRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        String email = "nonexistent@example.com";

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn(email);
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");

        verify(authComponent).decodeJwt(refreshToken);
        verify(decodedJWT).getClaim(AuthComponent.ClaimUserEmailKey);
        verify(decodedJWT).getClaim(AuthComponent.ClaimRefreshKey);
        verify(userPersistentRepository).findByEmail(email);
        verifyNoInteractions(encryptionComponent);
    }
}