package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplGeneratedAiTests {

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private UserPersistentRepository userPersistentRepository;

    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void login_withValidCredentials_returnsAuthResponse() {
        // GIVEN
        String email = "user@example.com";
        String password = "password";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("encrypted");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "encrypted")).thenReturn(true);

        Credential token = new Credential("token123", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refresh123", "Bearer", new Date(), true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refresh123");
        assertThat(response.getTokenType()).isEqualTo("Bearer");
    }

    @Test
    void login_withInvalidEmail_throwsUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withInvalidPassword_throwsUnAuthorized() {
        // GIVEN
        String email = "user@example.com";
        String password = "wrong";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("encrypted");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "encrypted")).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withValidRefreshToken_returnsAuthResponse() {
        // GIVEN
        String refreshToken = "refreshToken";
        String email = "user@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);

        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn(email);
        when(refreshClaim.asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("token123", "Bearer", new Date(), false);
        Credential newRefreshToken = new Credential("refresh123", "Bearer", new Date(), true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(newRefreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshToken);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refresh123");
    }

    @Test
    void login_withInvalidRefreshToken_throwsUnAuthorized() {
        // GIVEN
        String refreshToken = "invalidRefresh";
        String email = "user@example.com";

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);

        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn(email);
        when(refreshClaim.asBoolean()).thenReturn(false);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }
}
