package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        Credential token = new Credential("Bearer", "accessToken", new Date(), false);
        Credential refreshToken = new Credential("Bearer", "refreshToken", new Date(), true);

        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);
        Mockito.when(authComponent.generateJwt(String.valueOf(user.getId()), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(String.valueOf(user.getId()), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo(token.getTokenType());
        assertThat(response.getToken()).isEqualTo(token.getToken());
        assertThat(response.getRefreshToken()).isEqualTo(refreshToken.getToken());
        assertThat(response.getExpiresAt()).isEqualTo(token.getExp());

        Mockito.verify(userPersistentRepository).findByEmail(email);
        Mockito.verify(encryptionComponent).matchedPassword(password, user.getPassword());
        Mockito.verify(authComponent).generateJwt(String.valueOf(user.getId()), user.getEmail(), false);
        Mockito.verify(authComponent).generateJwt(String.valueOf(user.getId()), user.getEmail(), true);
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";

        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Email or password is invalid");

        Mockito.verify(userPersistentRepository).findByEmail(email);
        Mockito.verifyNoInteractions(encryptionComponent, authComponent);
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Email or password is invalid");

        Mockito.verify(userPersistentRepository).findByEmail(email);
        Mockito.verify(encryptionComponent).matchedPassword(password, user.getPassword());
        Mockito.verifyNoInteractions(authComponent);
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshToken = "validRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        boolean isRefresh = true;

        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        Credential token = new Credential("Bearer", "accessToken", new Date(), false);
        Credential newRefreshToken = new Credential("Bearer","newRefreshToken", new Date(), true);

        Mockito.when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(isRefresh);
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(authComponent.generateJwt(String.valueOf(user.getId()), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(String.valueOf(user.getId()), user.getEmail(), true)).thenReturn(newRefreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshToken);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo(token.getTokenType());
        assertThat(response.getToken()).isEqualTo(token.getToken());
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken.getToken());
        assertThat(response.getExpiresAt()).isEqualTo(token.getExp());

        Mockito.verify(authComponent).decodeJwt(refreshToken);
        Mockito.verify(decodedJWT).getClaim(AuthComponent.ClaimUserEmailKey);
        Mockito.verify(decodedJWT).getClaim(AuthComponent.ClaimRefreshKey);
        Mockito.verify(userPersistentRepository).findByEmail(email);
        Mockito.verify(authComponent).generateJwt(String.valueOf(user.getId()), user.getEmail(), false);
        Mockito.verify(authComponent).generateJwt(String.valueOf(user.getId()), user.getEmail(), true);
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        boolean isRefresh = false;

        Mockito.when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(isRefresh);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");

        Mockito.verify(authComponent).decodeJwt(refreshToken);
        Mockito.verify(decodedJWT).getClaim(AuthComponent.ClaimUserEmailKey);
        Mockito.verify(decodedJWT).getClaim(AuthComponent.ClaimRefreshKey);
        Mockito.verifyNoInteractions(userPersistentRepository, encryptionComponent);
    }

    @Test
    void givenNonExistentUser_whenLoginWithRefreshToken_thenThrowUnAuthorized() {
        // GIVEN
        String refreshToken = "validRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "nonexistent@example.com";
        boolean isRefresh = true;

        Mockito.when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(isRefresh);
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(refreshToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token invalid");

        Mockito.verify(authComponent).decodeJwt(refreshToken);
        Mockito.verify(decodedJWT).getClaim(AuthComponent.ClaimUserEmailKey);
        Mockito.verify(decodedJWT).getClaim(AuthComponent.ClaimRefreshKey);
        Mockito.verify(userPersistentRepository).findByEmail(email);
        Mockito.verifyNoInteractions(encryptionComponent);
    }
}
