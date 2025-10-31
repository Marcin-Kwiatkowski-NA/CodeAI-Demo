package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        encryptionComponent = Mockito.mock(BCryptPasswordEncryptionComponent.class);
        authComponent = Mockito.mock(AuthComponent.class);
        userPersistentRepository = Mockito.mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void givenValidCredentials_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User("1", "username", email, "hashedPassword");
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User("1", "username", email, "hashedPassword");
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class);
        Mockito.when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        User user = new User("1", "username", "test@example.com", "hashedPassword");
        Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }