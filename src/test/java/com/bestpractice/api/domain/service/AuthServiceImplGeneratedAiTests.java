package com.bestpractice.api.domain.service;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        AuthResponse response = authServiceImpl.login(email, password);

        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        String refreshTokenValue = "refreshTokenValue";
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        AuthResponse response = authServiceImpl.login(refreshTokenValue);

        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }