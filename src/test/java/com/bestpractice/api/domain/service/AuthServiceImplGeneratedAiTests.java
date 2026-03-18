package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @BeforeEach
    void setUp() {
        Mockito.reset(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void login_withValidCredentials_shouldReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User("1", "username", email, "hashedPassword");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("tokenValue");
        assertThat(response.getRefreshToken()).isEqualTo("refreshTokenValue");
        assertThat(response.getTokenType()).isEqualTo("Bearer");
    }

    @Test
    void login_withInvalidEmail_shouldThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN THEN
        assertThatThrownBy(() -> authServiceImpl.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withInvalidPassword_shouldThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User("1", "username", email, "hashedPassword");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN THEN
        assertThatThrownBy(() -> authServiceImpl.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withValidRefreshToken_shouldReturnAuthResponse() {
        // GIVEN
        String refreshToken = "refreshTokenValue";
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);
        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("test@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        User user = new User("1", "username", "test@example.com", "hashedPassword");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refresh = new Credential("refreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refresh);

        // WHEN
        AuthResponse response = authServiceImpl.login(refreshToken);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("tokenValue");
        assertThat(response.getRefreshToken()).isEqualTo("refreshTokenValue");
    }

    @Test
    void login_withInvalidRefreshToken_shouldThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "invalidToken";
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);
        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("test@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);
        User user = new User("1", "username", "test@example.com", "hashedPassword");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);

        // WHEN THEN
        assertThatThrownBy(() -> authServiceImpl.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }

    @Test
    void login_withNonExistingUserInRefreshToken_shouldThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "refreshTokenValue";
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);
        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn("nonexistent@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(anyString())).thenReturn(null);

        // WHEN THEN
        assertThatThrownBy(() -> authServiceImpl.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }
}
