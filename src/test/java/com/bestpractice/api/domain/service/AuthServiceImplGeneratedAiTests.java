package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class AuthServiceImplGeneratedAiTests {

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    @Mock
    private AuthComponent authComponent;

    @Mock
    private UserPersistentRepository userPersistentRepository;

    @InjectMocks
    private AuthServiceImpl authServiceImpl;

    private User user;
    private Credential accessCredential;
    private Credential refreshCredential;

    @BeforeEach
    void setUp() {
        user = new User("user123", "john", "john@example.com", "hashedPassword");
        accessCredential = new Credential("accessToken", "Bearer", new Date(System.currentTimeMillis() + 3600_000), false);
        refreshCredential = new Credential("refreshToken", "Bearer", null, true);
        reset(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void testLoginWithValidCredentialsReturnsAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(encryptionComponent.matchedPassword("plainPassword", user.getPassword())).thenReturn(true);
        when(authComponent.generateJwt(eq(user.getId()), eq(user.getEmail()), eq(false))).thenReturn(accessCredential);
        when(authComponent.generateJwt(eq(user.getId()), eq(user.getEmail()), eq(true))).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login(user.getEmail(), "plainPassword");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getAccessToken()).isEqualTo("accessToken");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken");
        assertThat(response.getTokenType()).isEqualTo("Bearer");
    }

    @Test
    void testLoginWithInvalidEmailThrowsUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("unknown@example.com")).thenReturn(null);

        // WHEN
        UnAuthorized thrown = assertThatThrownBy(() -> authServiceImpl.login("unknown@example.com", "anyPassword"))
                .isInstanceOf(UnAuthorized.class);

        // THEN
        assertThat(thrown).hasMessageContaining("User not found");
    }

    @Test
    void testLoginWithInvalidPasswordThrowsUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(encryptionComponent.matchedPassword("wrongPassword", user.getPassword())).thenReturn(false);

        // WHEN
        UnAuthorized thrown = assertThatThrownBy(() -> authServiceImpl.login(user.getEmail(), "wrongPassword"))
                .isInstanceOf(UnAuthorized.class);

        // THEN
        assertThat(thrown).hasMessageContaining("Password does not match");
    }

    @Test
    void testLoginWithValidRefreshTokenReturnsAuthResponse() {
        // GIVEN
        DecodedJWT decodedJwt = mock(DecodedJWT.class);
        when(authComponent.decodeJwt("validRefreshToken")).thenReturn(decodedJwt);
        when(decodedJwt.getClaim("email")).thenReturn(() -> "john@example.com");
        when(decodedJwt.getClaim("isRefresh")).thenReturn(() -> true);
        when(userPersistentRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(authComponent.generateJwt(eq(user.getId()), eq(user.getEmail()), eq(false))).thenReturn(accessCredential);
        when(authComponent.generateJwt(eq(user.getId()), eq(user.getEmail()), eq(true))).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("validRefreshToken");

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getAccessToken()).isEqualTo("accessToken");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    void testLoginWithNonRefreshTokenThrowsUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJwt = mock(DecodedJWT.class);
        when(authComponent.decodeJwt("nonRefreshToken")).thenReturn(decodedJwt);
        when(decodedJwt.getClaim("email")).thenReturn(() -> "john@example.com");
        when(decodedJwt.getClaim("isRefresh")).thenReturn(() -> false);
        when(userPersistentRepository.findByEmail(user.getEmail())).thenReturn(user);

        // WHEN
        UnAuthorized thrown = assertThatThrownBy(() -> authServiceImpl.login("nonRefreshToken"))
                .isInstanceOf(UnAuthorized.class);

        // THEN
        assertThat(thrown).hasMessageContaining("Token is not a refresh token");
    }

    @Test
    void testLoginWithInvalidRefreshTokenThrowsUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJwt = mock(DecodedJWT.class);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJwt);
        when(decodedJwt.getClaim("email")).thenReturn(() -> "john@example.com");
        when(decodedJwt.getClaim("isRefresh")).thenReturn(() -> true);
        when(userPersistentRepository.findByEmail(user.getEmail())).thenReturn(null);

        // WHEN
        UnAuthorized thrown = assertThatThrownBy(() -> authServiceImpl.login("invalidRefreshToken"))
                .isInstanceOf(UnAuthorized.class);

        // THEN
        assertThat(thrown).hasMessageContaining("User not found");
    }

    @Test
    void testLoginWithExpiredTokenThrowsUnAuthorized() {
        // GIVEN
        when(authComponent.decodeJwt("expiredToken")).thenThrow(new UnAuthorized("Token expired"));

        // WHEN
        UnAuthorized thrown = assertThatThrownBy(() -> authServiceImpl.login("expiredToken"))
                .isInstanceOf(UnAuthorized.class);

        // THEN
        assertThat(thrown).hasMessageContaining("Token expired");
    }

    @Test
    void testLoginGeneratesJwtWithCorrectParameters() {
        // GIVEN
        when(userPersistentRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(encryptionComponent.matchedPassword("plainPassword", user.getPassword())).thenReturn(true);
        when(authComponent.generateJwt(eq(user.getId()), eq(user.getEmail()), eq(false))).thenReturn(accessCredential);
        when(authComponent.generateJwt(eq(user.getId()), eq(user.getEmail()), eq(true))).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login(user.getEmail(), "plainPassword");

        // THEN
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Boolean> refreshCaptor = ArgumentCaptor.forClass(Boolean.class);

        verify(authComponent, times(2)).generateJwt(idCaptor.capture(), emailCaptor.capture(), refreshCaptor.capture());

        assertThat(idCaptor.getAllValues()).containsExactly(user.getId(), user.getId());
        assertThat(emailCaptor.getAllValues()).containsExactly(user.getEmail(), user.getEmail());
        assertThat(refreshCaptor.getAllValues()).containsExactly(false, true);
        assertThat(response.getAccessToken()).isEqualTo("accessToken");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken");
    }
}
