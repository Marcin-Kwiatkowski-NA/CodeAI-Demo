package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

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
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

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

    private User user;
    private String email;
    private String password;
    private Credential accessCredential;
    private Credential refreshCredential;

    @BeforeEach
    void setUp() {
        email = "user@example.com";
        password = "password123";
        user = new User("user-id-1", "john", email, "hashedPassword");

        accessCredential = Mockito.mock(Credential.class);
        refreshCredential = Mockito.mock(Credential.class);

        Mockito.when(accessCredential.getTokenType()).thenReturn("Bearer");
        Mockito.when(accessCredential.getToken()).thenReturn("accessToken");
        Mockito.when(accessCredential.getExp()).thenReturn(new Date());

        Mockito.when(refreshCredential.getToken()).thenReturn("refreshToken");
    }

    @Test
    void testLoginWithValidCredentialsReturnsAuthResponse() {
        // GIVEN
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(accessCredential);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo("Bearer");
        assertThat(response.getToken()).isEqualTo("accessToken");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken");
        assertThat(response.getExp()).isEqualTo(accessCredential.getExp());
        Mockito.verify(authComponent).generateJwt(user.getId(), user.getEmail(), false);
        Mockito.verify(authComponent).generateJwt(user.getId(), user.getEmail(), true);
    }

    @Test
    void testLoginWithInvalidEmailThrowsUnAuthorized() {
        // GIVEN
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void testLoginWithInvalidPasswordThrowsUnAuthorized() {
        // GIVEN
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Email or password is invalid");
    }

    @Test
    void testLoginWithValidRefreshTokenReturnsAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);

        Mockito.when(authComponent.decodeJwt("validRefreshToken")).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(emailClaim.asString()).thenReturn(email);
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);

        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(accessCredential);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authService.login("validRefreshToken");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo("accessToken");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken");
        Mockito.verify(authComponent).generateJwt(user.getId(), user.getEmail(), false);
        Mockito.verify(authComponent).generateJwt(user.getId(), user.getEmail(), true);
    }

    @Test
    void testLoginWithInvalidRefreshTokenThrowsUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);

        Mockito.when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(emailClaim.asString()).thenReturn(email);
        Mockito.when(refreshClaim.asBoolean()).thenReturn(false);

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login("invalidRefreshToken"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token invalid");
    }

    @Test
    void testLoginWithExpiredRefreshTokenThrowsUnAuthorized() {
        // GIVEN
        Mockito.when(authComponent.decodeJwt("expiredToken")).thenThrow(new UnAuthorized("Token is expired time"));

        // WHEN & THEN
        assertThatThrownBy(() -> authService.login("expiredToken"))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token is expired time");
    }
}
