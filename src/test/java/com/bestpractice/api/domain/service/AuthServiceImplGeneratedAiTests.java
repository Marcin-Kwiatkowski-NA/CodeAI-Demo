package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verify;

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
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("encryptedPassword");

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
        assertThat(response.getToken()).isEqualTo(token.getToken());
        assertThat(response.getRefreshToken()).isEqualTo(refreshToken.getToken());
        verify(userPersistentRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(password, user.getPassword());
        verify(authComponent, times(1)).generateJwt(user.getId(), user.getEmail(), false);
        verify(authComponent, times(1)).generateJwt(user.getId(), user.getEmail(), true);
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";

        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Email or password is invalid");
        verify(userPersistentRepository, times(1)).findByEmail(email);
        verifyNoInteractions(encryptionComponent, authComponent);
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Email or password is invalid");
        verify(userPersistentRepository, times(1)).findByEmail(email);
        verify(encryptionComponent, times(1)).matchedPassword(password, user.getPassword());
        verifyNoInteractions(authComponent);
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshToken = "validRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("123");
        user.setEmail(email);

        Credential token = new Credential("token", "Bearer", null, false);
        Credential newRefreshToken = new Credential("newRefreshToken", "Bearer", null, true);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(newRefreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshToken);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo(token.getToken());
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken.getToken());
        verify(authComponent, times(1)).decodeJwt(refreshToken);
        verify(userPersistentRepository, times(1)).findByEmail(email);
        verify(authComponent, times(1)).generateJwt(user.getId(), user.getEmail(), false);
        verify(authComponent, times(1)).generateJwt(user.getId(), user.getEmail(), true);
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");
        verify(authComponent, times(1)).decodeJwt(refreshToken);
        verifyNoInteractions(userPersistentRepository, encryptionComponent);
    }

    @Test
    void givenNonExistentUserForRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "validRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "nonexistent@example.com";

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");
        verify(authComponent, times(1)).decodeJwt(refreshToken);
        verify(userPersistentRepository, times(1)).findByEmail(email);
        verifyNoInteractions(encryptionComponent);
    }
}
