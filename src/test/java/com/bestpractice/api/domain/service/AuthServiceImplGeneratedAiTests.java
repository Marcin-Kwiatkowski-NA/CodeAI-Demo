package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.auth0.jwt.interfaces.Claim;
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
        tokenCredential = new Credential("Bearer", "Bearer", new Date(System.currentTimeMillis() + 10000), false);
        refreshCredential = new Credential("Bearer", "refreshToken", new Date(System.currentTimeMillis() + 20000), true);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("password", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("test@example.com", "password");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("Bearer", response.getToken());
        assertEquals("refreshToken", response.getRefreshToken());
        assertEquals(tokenCredential.getExp(), response.getExpiresAt());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        when(userPersistentRepository.findByEmail("invalid@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalid@example.com", "password"));
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
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("test@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("refreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshToken");

        // THEN
        assertNotNull(response);
        assertEquals("Bearer", response.getTokenType());
        assertEquals("Bearer", response.getToken());
        assertEquals("refreshToken", response.getRefreshToken());
        assertEquals(tokenCredential.getExp(), response.getExpiresAt());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT= mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("test@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenForNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("nonexisting@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("refreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("nonexisting@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("refreshToken"));
    }
}