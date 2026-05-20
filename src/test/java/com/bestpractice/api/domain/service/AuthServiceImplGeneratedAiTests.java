package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
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
    private AuthServiceImpl authServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.reset(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        User user = new User();
        user.setId("1");
        user.setEmail("test@example.com");
        user.setPassword("hashedPassword");

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        Mockito.when(authComponent.generateJwt(eq("1"), eq("test@example.com"), eq(false))).thenReturn(token);
        Mockito.when(authComponent.generateJwt(eq("1"), eq("test@example.com"), eq(true))).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "plainPassword");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("tokenValue");
        assertThat(response.getRefreshToken()).isEqualTo("refreshTokenValue");
        assertThat(response.getTokenType()).isEqualTo("Bearer");
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        Mockito.when(userPersistentRepository.findByEmail(anyString())).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("invalid@example.com", "password"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        User user = new User();
        user.setId("1");
        user.setEmail("test@example.com");
        user.setPassword("hashedPassword");

        Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("test@example.com", "wrongPassword"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnNewAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        User user = new User();
        user.setId("1");
        user.setEmail("test@example.com");

        Credential token = new Credential("newToken", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("newRefreshToken", "Bearer", new Date(), true);

        Mockito.when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(Mockito.mock(com.auth0.jwt.interfaces.Claim.class));
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(Mockito.mock(com.auth0.jwt.interfaces.Claim.class));

        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Mockito.when(authComponent.generateJwt(eq("1"), eq("test@example.com"), eq(false))).thenReturn(token);
        Mockito.when(authComponent.generateJwt(eq("1"), eq("test@example.com"), eq(true))).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("newToken");
        assertThat(response.getRefreshToken()).isEqualTo("newRefreshToken");
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Mockito.when(authComponent.decodeJwt("invalidToken")).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(Mockito.mock(com.auth0.jwt.interfaces.Claim.class));
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(Mockito.mock(com.auth0.jwt.interfaces.Claim.class));

        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("unknown@example.com");
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);
        Mockito.when(userPersistentRepository.findByEmail("unknown@example.com")).thenReturn(null);

        // WHEN / THEN
        assertThatThrownBy(() -> authServiceImpl.login("invalidToken"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }
}
