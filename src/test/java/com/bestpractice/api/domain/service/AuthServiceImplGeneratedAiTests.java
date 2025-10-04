package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    private User testUser;
    private Credential tokenCredential;
    private Credential refreshCredential;

    @BeforeEach
    public void setUp() {
        testUser = new User("1", "testuser", "test@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date());
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date());
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("wrong@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("wrong@example.com", "plainPassword"));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("test@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> true);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> false);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenWithpackage com.bestpractice.api.domain.service;

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

    private User testUser;
    private Credential tokenCredential;
    private Credential refreshCredential;

    @BeforeEach
    public void setUp() {
        testUser = new User("1", "testuser", "test@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date());
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date());
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("wrong@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("wrong@example.com", "plainPassword"));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("test@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> true);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> false);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenWithpackage com.bestpractice.api.domain.service;

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

    private User testUser;
    private Credential tokenCredential;
    private Credential refreshCredential;

    @BeforeEach
    public void setUp() {
        testUser = new User("1", "testuser", "test@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date());
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date());
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("wrong@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("wrong@example.com", "plainPassword"));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("test@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> true);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> false);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenWithpackage com.bestpractice.api.domain.service;

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

    private User testUser;
    private Credential tokenCredential;
    private Credential refreshCredential;

    @BeforeEach
    public void setUp() {
        testUser = new User("1", "testuser", "test@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date());
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date());
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("wrong@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("wrong@example.com", "plainPassword"));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("test@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> true);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> false);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenWithpackage com.bestpractice.api.domain.service;

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

    private User testUser;
    private Credential tokenCredential;
    private Credential refreshCredential;

    @BeforeEach
    public void setUp() {
        testUser = new User("1", "testuser", "test@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date());
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date());
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("wrong@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("wrong@example.com", "plainPassword"));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("test@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> true);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> false);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenWithpackage com.bestpractice.api.domain.service;

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

    private User testUser;
    private Credential tokenCredential;
    private Credential refreshCredential;

    @BeforeEach
    public void setUp() {
        testUser = new User("1", "testuser", "test@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date());
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date());
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("wrong@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("wrong@example.com", "plainPassword"));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("test@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> true);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> false);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenWithpackage com.bestpractice.api.domain.service;

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

    private User testUser;
    private Credential tokenCredential;
    private Credential refreshCredential;

    @BeforeEach
    public void setUp() {
        testUser = new User("1", "testuser", "test@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date());
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date());
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("wrong@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("wrong@example.com", "plainPassword"));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("test@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> true);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("tokenValue", response.getToken());
        assertEquals("refreshTokenValue", response.getRefreshToken());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(() -> "test@example.com");
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(() -> false);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }
