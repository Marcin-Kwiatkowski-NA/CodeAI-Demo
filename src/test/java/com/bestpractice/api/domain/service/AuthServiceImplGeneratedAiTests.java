package com.bestpractice.api.domain.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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

import static com.bestpractice.api.domain.component.AuthComponent.ClaimRefreshKey;
import static com.bestpractice.api.domain.component.AuthComponent.ClaimUserEmailKey;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
        reset(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void loginWithValidCredentialsReturnsAuthResponse() {
        // GIVEN
        String email = "user@example.com";
        String password = "plainPassword";
        User user = new User("id1", "username", email, "hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = mock(Credential.class);
        when(token.getTokenType()).thenReturn("Bearer");
        when(token.getToken()).thenReturn("token123");
        when(token.getExp()).thenReturn(12345L);

        Credential refreshToken = mock(Credential.class);
        when(refreshToken.getToken()).thenReturn("refresh123");

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo("Bearer");
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refresh123");
        assertThat(response.getExp()).isEqualTo(12345L);

        verify(authComponent, times(1)).generateJwt(user.getId(), user.getEmail(), false);
        verify(authComponent, times(1)).generateJwt(user.getId(), user.getEmail(), true);
    }

    @Test
    void loginWithInvalidEmailThrowsUnAuthorized() {
        // GIVEN
        String email = "nonexistent@example.com";
        String password = "anyPassword";

        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class,
                () -> authServiceImpl.login(email, password));
        assertThat(exception.getMessage()).isEqualTo("Email or password is invalid");
    }

    @Test
    void loginWithInvalidPasswordThrowsUnAuthorized() {
        // GIVEN
        String email = "user@example.com";
        String password = "wrongPassword";
        User user = new User("id1", "username", email, "hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class,
                () -> authServiceImpl.login(email, password));
        assertThat(exception.getMessage()).isEqualTo("Email or password is invalid");
    }

    @Test
    void loginWithRefreshTokenValidReturnsAuthResponse() {
        // GIVEN
        String refreshToken = "someRefreshToken";
        String email = "user@example.com";
        User user = new User("id1", "username", email, "hashedPassword");

        DecodedJWT decodedJwt = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJwt);
        when(decodedJwt.getClaim(ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJwt.getClaim(ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn(email);
        when(refreshClaim.asBoolean()).thenReturn(true);

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = mock(Credential.class);
        when(token.getTokenType()).thenReturn("Bearer");
        when(token.getToken()).thenReturn("newToken");
        when(token.getExp()).thenReturn(67890L);

        Credential rToken = mock(Credential.class);
        when(rToken.getToken()).thenReturn("newRefreshToken");

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(rToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(refreshToken);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo("Bearer");
        assertThat(response.getToken()).isEqualTo("newToken");
        assertThat(response.getRefreshToken()).isEqualTo("newRefreshToken");
        assertThat(response.getExp()).isEqualTo(67890L);

        verify(authComponent, times(1)).generateJwt(user.getId(), user.getEmail(), false);
        verify(authComponent, times(1)).generateJwt(user.getId(), user.getEmail(), true);
    }

    @Test
    void loginWithRefreshTokenInvalidUserThrowsUnAuthorized() {
        // GIVEN
        String refreshToken = "someRefreshToken";
        String email = "unknown@example.com";

        DecodedJWT decodedJwt = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJwt);
        when(decodedJwt.getClaim(ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJwt.getClaim(ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn(email);
        when(refreshClaim.asBoolean()).thenReturn(true);

        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class,
                () -> authServiceImpl.login(refreshToken));
        assertThat(exception.getMessage()).isEqualTo("Token invalid");
    }

    @Test
    void loginWithRefreshTokenNotRefreshFlagThrowsUnAuthorized() {
        // GIVEN
        String refreshToken = "someRefreshToken";
        String email = "user@example.com";

        DecodedJWT decodedJwt = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);

        when(authComponent.decodeJwt(refreshToken)).thenReturn(decodedJwt);
        when(decodedJwt.getClaim(ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJwt.getClaim(ClaimRefreshKey)).thenReturn(refreshClaim);
        when(emailClaim.asString()).thenReturn(email);
        when(refreshClaim.asBoolean()).thenReturn(false);

        // WHEN & THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class,
                () -> authServiceImpl.login(refreshToken));
        assertThat(exception.getMessage()).isEqualTo("Token invalid");
    }
}
