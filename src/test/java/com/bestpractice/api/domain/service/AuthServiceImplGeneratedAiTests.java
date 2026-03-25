package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.mock;
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
    void login_withValidEmailAndPassword_shouldReturnAuthResponse() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "hashedPassword");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("password", "hashedPassword")).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "password");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("tokenValue");
        assertThat(response.getRefreshToken()).isEqualTo("refreshTokenValue");
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
        User user = new User("1", "testuser", "test@example.com", "hashedPassword");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("test@example.com", "wrongPassword"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void login_withValidRefreshToken_shouldReturnNewAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        when(authComponent.decodeJwt("refreshToken")).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        User user = new User("1", "testuser", "test@example.com", "hashedPassword");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Credential token = new Credential("newToken", "Bearer", null, false);
        Credential refreshToken = new Credential("newRefreshToken", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshToken");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("newToken");
        assertThat(response.getRefreshToken()).isEqualTo("newRefreshToken");
        assertThat(response.getTokenType()).isEqualTo("Bearer");
    }

    @Test
    void login_withInvalidRefreshToken_shouldThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        when(authComponent.decodeJwt("invalidToken")).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);
        User user = new User("1", "testuser", "test@example.com", "hashedPassword");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("invalidToken"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }

    @Test
    void login_withRefreshTokenForNonExistingUser_shouldThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        when(authComponent.decodeJwt("refreshToken")).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("nonexistent@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("refreshToken"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }
}
