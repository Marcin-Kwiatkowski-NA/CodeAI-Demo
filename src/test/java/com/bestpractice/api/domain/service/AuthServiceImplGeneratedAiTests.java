package com.bestpractice.api.domain.service;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password123";
        User user = new User("1", "testuser", email, "encryptedPassword");
        Credential token = new Credential("token", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshToken", "Bearer", null, true);

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
        verify(userPersistentRepository).findByEmail(email);
        verify(encryptionComponent).matchedPassword(password, user.getPassword());
        verify(authComponent, times(2)).generateJwt(user.getId(), user.getEmail(), anyBoolean());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
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
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User("1", "testuser", email, "encryptedPassword");

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
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshToken = "validRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        User user = new User("1", "testuser", "test@example.com", "encryptedPassword");
        Credential token = new Credential("token", "Bearer", null, false);
        Credential newRefreshToken = new Credential("newRefreshToken", "Bearer", null, true);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(user.getEmail());
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(newRefreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshToken);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo(token.getTokenType());
        assertThat(response.getToken()).isEqualTo(token.getToken());
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken.getToken());
        verify(authComponent).decodeJwt(refreshToken);
        verify(decodedJWT).getClaim(AuthComponent.ClaimUserEmailKey);
        verify(decodedJWT).getClaim(AuthComponent.ClaimRefreshKey);
        verify(userPersistentRepository).findByEmail(user.getEmail());
        verify(authComponent, times(2)).generateJwt(user.getId(), user.getEmail(), anyBoolean());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

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
    void givenNonExistentUserEmailInRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "validRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("nonexistent@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");
        verify(authComponent).decodeJwt(refreshToken);
        verify(decodedJWT).getClaim(AuthComponent.ClaimUserEmailKey);
        verify(decodedJWT).getClaim(AuthComponent.ClaimRefreshKey);
        verify(userPersistentRepository).findByEmail("nonexistent@example.com");
        verifyNoInteractions(encryptionComponent);
    }
}