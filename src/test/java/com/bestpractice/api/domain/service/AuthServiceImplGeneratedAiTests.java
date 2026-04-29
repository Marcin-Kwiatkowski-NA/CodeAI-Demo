package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
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
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        if (authService == null) {
            authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
        }
        Mockito.reset(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void login_withValidCredentials_shouldReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User("1", "username", email, "encryptedPassword");
        Credential token = Mockito.mock(Credential.class);
        Credential refreshToken = Mockito.mock(Credential.class);

        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);
        Mockito.when(token.getTokenType()).thenReturn("Bearer");
        Mockito.when(token.getToken()).thenReturn("token123");
        Mockito.when(refreshToken.getToken()).thenReturn("refreshToken123");

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken123");
        assertThat(response.getTokenType()).isEqualTo("Bearer");
    }

    @Test
    void login_withInvalidEmail_shouldThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withInvalidPassword_shouldThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User("1", "username", email, "encryptedPassword");
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withValidRefreshToken_shouldReturnNewTokens() {
        // GIVEN
        String refreshToken = "refreshToken123";
        String email = "test@example.com";
        User user = new User("1", "username", email, "encryptedPassword");
        com.auth0.jwt.interfaces.DecodedJWT decodedJWT = Mockito.mock(com.auth0.jwt.interfaces.DecodedJWT.class);
        com.auth0.jwt.interfaces.Claim emailClaim = Mockito.mock(com.auth0.jwt.interfaces.Claim.class);
        com.auth0.jwt.interfaces.Claim refreshClaim = Mockito.mock(com.auth0.jwt.interfaces.Claim.class);
        Credential token = Mockito.mock(Credential.class);
        Credential newRefreshToken = Mockito.mock(Credential.class);

        Mockito.when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(emailClaim.asString()).thenReturn(email);
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(newRefreshToken);
        Mockito.when(token.getToken()).thenReturn("newToken123");
        Mockito.when(newRefreshToken.getToken()).thenReturn("newRefreshToken123");

        // WHEN
        AuthResponse response = authService.login(refreshToken);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("newToken123");
        assertThat(response.getRefreshToken()).isEqualTo("newRefreshToken123");
    }

    @Test
    void login_withInvalidRefreshToken_shouldThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "invalidToken";
        com.auth0.jwt.interfaces.DecodedJWT decodedJWT = Mockito.mock(com.auth0.jwt.interfaces.DecodedJWT.class);
        com.auth0.jwt.interfaces.Claim emailClaim = Mockito.mock(com.auth0.jwt.interfaces.Claim.class);
        com.auth0.jwt.interfaces.Claim refreshClaim = Mockito.mock(com.auth0.jwt.interfaces.Claim.class);

        Mockito.when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(emailClaim.asString()).thenReturn("test@example.com");
        Mockito.when(refreshClaim.asBoolean()).thenReturn(false);
        Mockito.when(userPersistentRepository.findByEmail(anyString())).thenReturn(new User("1", "username", "test@example.com", "encryptedPassword"));

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }

    @Test
    void login_withNonExistingUserInRefreshToken_shouldThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "refreshToken123";
        com.auth0.jwt.interfaces.DecodedJWT decodedJWT = Mockito.mock(com.auth0.jwt.interfaces.DecodedJWT.class);
        com.auth0.jwt.interfaces.Claim emailClaim = Mockito.mock(com.auth0.jwt.interfaces.Claim.class);
        com.auth0.jwt.interfaces.Claim refreshClaim = Mockito.mock(com.auth0.jwt.interfaces.Claim.class);

        Mockito.when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(emailClaim.asString()).thenReturn("nonexistent@example.com");
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);
        Mockito.when(userPersistentRepository.findByEmail(eq("nonexistent@example.com"))).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }
}
