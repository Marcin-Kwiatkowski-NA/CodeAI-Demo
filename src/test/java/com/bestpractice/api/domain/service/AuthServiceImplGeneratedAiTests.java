package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

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
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        User user = new User();
        user.setId("123");
        user.setEmail("test@example.com");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("newRefreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("newRefreshTokenValue", response.getRefreshToken());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT =package com.bestpractice.api.domain.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

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
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        User user = new User();
        user.setId("123");
        user.setEmail("test@example.com");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("newRefreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("newRefreshTokenValue", response.getRefreshToken());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT =package com.bestpractice.api.domain.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

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
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        User user = new User();
        user.setId("123");
        user.setEmail("test@example.com");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("newRefreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("newRefreshTokenValue", response.getRefreshToken());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.classpackage com.bestpractice.api.domain.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

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
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        User user = new User();
        user.setId("123");
        user.setEmail("test@example.com");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("newRefreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("newRefreshTokenValue", response.getRefreshToken());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.classpackage com.bestpractice.api.domain.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

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
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        User user = new User();
        user.setId("123");
        user.setEmail("test@example.com");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("newRefreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("newRefreshTokenValue", response.getRefreshToken());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.classpackage com.bestpractice.api.domain.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(true);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

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
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("123");
        user.setEmail(email);
        user.setPassword("hashed");
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, "hashed")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn("test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        User user = new User();
        user.setId("123");
        user.setEmail("test@example.com");
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("newRefreshTokenValue", "Bearer", null, true);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("newRefreshTokenValue", response.getRefreshToken());
    }
