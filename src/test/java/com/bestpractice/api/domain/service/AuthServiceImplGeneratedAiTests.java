package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        testUser = new User("id123", "username", "email@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date(), false);
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date(), true);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("email@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals(tokenCredential.getTokenType(), response.getTokenType());
        assertEquals(tokenCredential.getToken(), response.getToken());
        assertEquals(refreshCredential.getToken(), response.getRefreshToken());
        assertEquals(tokenCredential.getExp(), response.getExpiresAt());
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
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("email@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("email@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals(tokenCredential.getTokenType(), response.getTokenType());
        assertEquals(tokenCredential.getToken(), response.getToken());
        assertEquals(refreshCredential.getToken(), response.getRefreshToken());
        assertEquals(tokenCredential.getExp(), response.getExpiresAt());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("email@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenWithNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("nonexistent@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("refreshTokenValue"));
    }
}

/*
2025-10-06 12:22:31.271 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 12:22:31.275 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 12:22:31.275 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,57] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,85] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,39] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,57] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,85] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,39] not a statement
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 12:22:31.277 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 12:22:31.474 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 12:22:31.476 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 12:22:31.477 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 12:22:31.477 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 12:22:34.748 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 12:22:34.749 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 12:22:34.749 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,57] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,85] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,39] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,57] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,85] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,39] not a statement
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 12:22:34.751 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 12:22:34.999 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 12:22:35.002 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 12:22:35.002 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 12:22:35.002 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 12:22:38.117 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 12:22:38.118 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 12:22:38.118 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,57] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,85] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,39] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,57] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,85] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,39] not a statement
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 12:22:38.119 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 12:22:38.391 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 12:22:38.392 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 12:22:38.392 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 12:22:38.392 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 12:22:41.450 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 12:22:41.450 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 12:22:41.450 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,57] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,85] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,39] not a statement
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,57] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,85] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[138,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[139,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[140,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[141,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[142,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[143,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[144,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[145,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[146,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[147,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[148,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[149,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[150,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[151,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[153,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[155,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[156,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[267,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[269,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[270,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[271,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[272,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[273,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[274,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[275,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[276,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[277,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[278,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[279,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[280,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[281,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[282,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[284,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[286,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[287,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,31] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,30] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[398,66] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[400,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[401,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[402,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[403,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[404,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[405,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[406,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[407,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[408,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[409,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9856036220868817953/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[410,39] not a statement
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 12:22:41.450 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 12:22:41.965 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 12:22:41.968 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 12:22:41.968 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 12:22:41.968 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 13:08:11.435 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 13:08:11.437 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:08:11.437 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-17901140817028057135/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[133,17] <identifier> expected
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-17901140817028057135/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[133,17] <identifier> expected
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 13:08:11.437 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:08:16.903 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3186, outputTokenCount = 1024, totalTokenCount = 4210 }
2025-10-06 13:08:16.904 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-06 13:08:19.059 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4222, outputTokenCount = 302, totalTokenCount = 4524 }
2025-10-06 13:08:19.060 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:08:19.060 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;

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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExp());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshTokenValue";
        User user = new User();
        user.setId(1L);
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

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExp());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        Stringemail = "test@example.com";
        String refreshTokenValue = "invalidRefreshToken";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }

    @Test
    public void givenRefreshTokenWithNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "nonexisting@example.com";
        String refreshTokenValue = "refreshTokenValue";

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }
}
2025-10-06 13:08:19.060 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-06 13:08:19.062 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:08:19.062 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExp());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshTokenValue";
        User user = new User();
        user.setId(1L);
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

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExp());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        Stringemail = "test@example.com";
        String refreshTokenValue = "invalidRefreshToken";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }

    @Test
    public void givenRefreshTokenWithNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "nonexisting@example.com";
        String refreshTokenValue = "refreshTokenValue";

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }
}

2025-10-06 13:08:24.592 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-06 13:08:24.592 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:08:24.592 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Replace `Stringemail` with `String email`  
Ensure `email` variable is defined before use in all tests  
Remove or replace all `assertEquals(token.getExp(), response.getExp())` with a comparison using matching types

In this code:

package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExp());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshTokenValue";
        User user = new User();
        user.setId(1L);
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

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExp());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        Stringemail = "test@example.com";
        String refreshTokenValue = "invalidRefreshToken";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }

    @Test
    public void givenRefreshTokenWithNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "nonexisting@example.com";
        String refreshTokenValue = "refreshTokenValue";

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-06 13:08:24.593 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:08:29.837 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6028, outputTokenCount = 1024, totalTokenCount = 7052 }
2025-10-06 13:08:29.838 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-06 13:08:31.973 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7064, outputTokenCount = 314, totalTokenCount = 7378 }
2025-10-06 13:08:31.974 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:08:31.974 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;

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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshTokenValue";
        User user = new User();
        user.setId(1L);
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

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidRefreshToken_whenLoginthenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "invalidRefreshToken";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }

    @Test
    public void givenRefreshTokenWithNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "nonexisting@example.com";
        String refreshTokenValue = "refreshTokenValue";

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }
}
2025-10-06 13:08:31.974 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-06 13:08:31.976 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:08:31.976 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshTokenValue";
        User user = new User();
        user.setId(1L);
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

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidRefreshToken_whenLoginthenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "invalidRefreshToken";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }

    @Test
    public void givenRefreshTokenWithNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "nonexisting@example.com";
        String refreshTokenValue = "refreshTokenValue";

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }
}

2025-10-06 13:08:35.932 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-06 13:08:35.933 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:08:35.933 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Replace `Stringemail` with `String email`  
Ensure `email` variable is defined before use in all tests  
Remove or replace all `assertEquals(token.getExp(), response.getExp())` with a comparison using matching types

In this code:

package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshTokenValue";
        User user = new User();
        user.setId(1L);
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

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidRefreshToken_whenLoginthenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "invalidRefreshToken";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }

    @Test
    public void givenRefreshTokenWithNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "nonexisting@example.com";
        String refreshTokenValue = "refreshTokenValue";

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(refreshTokenValue));
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-06 13:08:35.933 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:08:41.237 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 8894, outputTokenCount = 1024, totalTokenCount = 9918 }
2025-10-06 13:08:41.238 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-06 13:08:41.488 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:135)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 13:08:41.493 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:08:41.493 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:08:41.493 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:86)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;

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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshTokenValue";
        User user = new User();
        user.setId(1L);
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

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin
2025-10-06 13:08:41.494 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-06 13:08:41.496 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:08:41.496 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshTokenValue";
        User user = new User();
        user.setId(1L);
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

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

2025-10-06 13:08:44.587 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-06 13:08:44.588 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:08:44.588 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:113)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Replace `Stringemail` with `String email`  
Ensure `email` variable is defined before use in all tests  
Remove or replace all `assertEquals(token.getExp(), response.getExp())` with a comparison using matching types

In this code:

package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplGeneratedAiTests {

    private BCryptPasswordEncryptionComponent encryptionComponent;
    private AuthComponent authComponent;
    private UserPersistentRepository userPersistentRepository;
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        encryptionComponent = mock(BCryptPasswordEncryptionComponent.class);
        authComponent = mock(AuthComponent.class);
        userPersistentRepository = mock(UserPersistentRepository.class);
        authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(true);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }

    @Test
    public void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword("encryptedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authService.login(email, password));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String email = "test@example.com";
        String refreshTokenValue = "refreshTokenValue";
        User user = new User();
        user.setId(1L);
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

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp().getTime(), response.getExp().getTime());
    }


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-06 13:08:44.588 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:117)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:08:44.758 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 13:08:44.761 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:08:44.761 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:08:44.761 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 13:27:17.532 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 13:27:17.534 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:27:17.534 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:115)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-8401309273670607687/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[54,20] incompatible types: long cannot be converted to java.lang.String
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-8401309273670607687/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[95,20] incompatible types: long cannot be converted to java.lang.String
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-8401309273670607687/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[113,20] incompatible types: long cannot be converted to java.lang.String
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-8401309273670607687/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[54,20] incompatible types: long cannot be converted to java.lang.String
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-8401309273670607687/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[95,20] incompatible types: long cannot be converted to java.lang.String
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-8401309273670607687/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[113,20] incompatible types: long cannot be converted to java.lang.String
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 13:27:17.534 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:27:24.173 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3669, outputTokenCount = 1024, totalTokenCount = 4693 }
2025-10-06 13:27:24.174 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-06 13:27:25.671 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4705, outputTokenCount = 170, totalTokenCount = 4875 }
2025-10-06 13:27:25.672 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:27:25.672 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:88)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;

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
        // GIVEN
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

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin@Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(refreshTokenValue));
    }
}
2025-10-06 13:27:25.673 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-06 13:27:25.676 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:91)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:27:25.676 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refined generated code:
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
        // GIVEN
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

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin@Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(refreshTokenValue));
    }
}

2025-10-06 13:27:29.815 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-06 13:27:29.815 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:27:29.815 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:115)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Remove the duplicated and malformed `@Test` annotation and method declaration fragment `@Test void givenInvalidRefreshToken_whenLogin` before the final test method.

In this code:

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
        // GIVEN
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

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin@Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(refreshTokenValue));
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-06 13:27:29.816 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:27:35.902 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6221, outputTokenCount = 1024, totalTokenCount = 7245 }
2025-10-06 13:27:35.903 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-06 13:27:38.040 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 7257, outputTokenCount = 194, totalTokenCount = 7451 }
2025-10-06 13:27:38.041 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:27:38.041 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:88)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generated code:
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
        // GIVEN
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

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(refreshTokenValue));
    }
}
2025-10-06 13:27:38.043 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-06 13:27:38.044 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:91)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:27:38.044 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refined generated code:
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
        // GIVEN
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

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(refreshTokenValue));
    }
}
2025-10-06 13:27:44.076 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-06 13:27:44.076 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:27:44.076 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:115)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Remove the duplicated and malformed `@Test` annotation and method declaration fragment `@Test void givenInvalidRefreshToken_whenLogin` before the final test method.

In this code:

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
        // GIVEN
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

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "invalidRefreshToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(false);
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(refreshTokenValue));
    }
}

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-06 13:27:44.077 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:27:49.387 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 8785, outputTokenCount = 1024, totalTokenCount = 9809 }
2025-10-06 13:27:49.387 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-06 13:27:49.639 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:137)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 13:27:49.641 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:123)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:27:49.641 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:27:49.641 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:88)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generated code:
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
        // GIVEN
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

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        String email = "test@example.com";
        User user = new User();
        user.setId("1");
        user.setEmail(email);

        when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).thenReturn(email);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).thenReturn(true);
        when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", new Date(), false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", new Date(), true);

        when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authServiceImpl.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken
2025-10-06 13:27:49.643 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-06 13:27:49.644 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:91)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:27:49.644 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refined generated code:
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
        // GIVEN
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

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }
2025-10-06 13:27:52.744 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-06 13:27:52.744 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:27:52.744 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:115)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Remove the duplicated and malformed `@Test` annotation and method declaration fragment `@Test void givenInvalidRefreshToken_whenLogin` before the final test method.

In this code:

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
        // GIVEN
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

        // WHEN
        AuthResponse response = authServiceImpl.login(email, password);

        // THEN
        assertNotNull(response);
        assertEquals(token.getTokenType(), response.getTokenType());
        assertEquals(token.getToken(), response.getToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
        assertEquals(token.getExp(), response.getExpiresAt());
    }

    @Test
    void givenInvalidEmail_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "invalid@example.com";
        String password = "password";
        when(userPersistentRepository.findByEmail(email)).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

    @Test
    void givenInvalidPassword_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String email = "test@example.com";
        String password = "wrongPassword";
        User user = new User();
        user.setId("1");
        user.setEmail(email);
        user.setPassword("hashedPassword");

        when(userPersistentRepository.findByEmail(email)).thenReturn(user);
        when(encryptionComponent.matchedPassword(password, user.getPassword())).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login(email, password));
    }

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-06 13:27:52.744 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:119)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:27:52.914 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:121)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 13:27:52.918 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:123)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:27:52.918 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:27:52.918 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 13:50:34.763 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 13:50:34.765 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:50:34.765 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[115,43] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[115,71] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[117,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[117,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[119,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[119,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[224,43] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[224,71] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[226,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[226,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[227,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[227,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[228,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[228,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[229,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[229,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[230,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[230,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[232,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[232,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[333,43] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[333,71] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[335,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[335,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[336,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[336,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[337,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[337,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[338,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[338,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[339,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[339,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[340,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[340,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[341,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[341,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[342,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[342,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[343,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[343,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[345,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[345,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[442,43] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[442,71] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[444,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[444,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[445,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[445,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[446,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[446,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[447,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[447,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[448,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[448,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[449,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[449,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[450,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[450,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[451,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[451,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[452,1] illegal start of expression
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[115,43] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[115,71] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[117,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[117,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[119,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[119,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[224,43] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[224,71] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[226,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[226,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[227,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[227,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[228,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[228,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[229,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[229,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[230,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[230,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[232,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[232,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[333,43] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[333,71] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[335,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[335,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[336,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[336,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[337,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[337,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[338,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[338,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[339,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[339,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[340,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[340,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[341,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[341,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[342,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[342,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[343,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[343,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[345,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[345,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[442,43] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[442,71] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[444,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[444,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[445,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[445,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[446,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[446,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[447,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[447,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[448,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[448,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[449,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[449,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[450,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[450,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[451,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[451,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-4255350308961533377/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[452,1] illegal start of expression
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 13:50:34.766 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:51:08.052 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 20265, outputTokenCount = 1024, totalTokenCount = 21289 }
2025-10-06 13:51:08.053 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-06 13:51:08.397 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:138)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 13:51:08.401 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:51:08.401 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:51:08.402 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:89)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;

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
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
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
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);
        String email = "test@example.com";
        User user = new User("1", "username", email, "hashedPassword");

        Mockito.when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(emailClaim.asString()).thenReturn(email);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
    }

    @Test
    void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        String refreshTokenValue = "refreshTokenValue";
        DecodedJWT decodedJWT = Mockito.mock(DecodedJWT.class
2025-10-06 13:51:08.403 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-06 13:51:08.404 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:92)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:51:08.404 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:93)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
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
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);
        String email = "test@example.com";
        User user = new User("1", "username", email, "hashedPassword");

        Mockito.when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(emailClaim.asString()).thenReturn(email);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
    }

2025-10-06 13:51:12.208 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-06 13:51:12.208 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:51:12.208 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Add a closing curly brace `}` at the end of the class.

In this code:

package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
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
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);
        String email = "test@example.com";
        User user = new User("1", "username", email, "hashedPassword");

        Mockito.when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(emailClaim.asString()).thenReturn(email);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
    }


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-06 13:51:12.209 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:51:12.356 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 13:51:12.360 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:51:12.360 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:51:12.360 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 13:51:15.411 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-06 13:51:15.411 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:51:15.411 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Add a closing curly brace `}` at the end of the class.

In this code:

package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
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
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);
        String email = "test@example.com";
        User user = new User("1", "username", email, "hashedPassword");

        Mockito.when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(emailClaim.asString()).thenReturn(email);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
    }


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-06 13:51:15.411 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:51:15.637 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 13:51:15.639 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:51:15.639 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:51:15.639 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 13:51:18.721 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:37)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-10-06 13:51:18.721 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 13:51:18.721 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Add a closing curly brace `}` at the end of the class.

In this code:

package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
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
        Claim emailClaim = Mockito.mock(Claim.class);
        Claim refreshClaim = Mockito.mock(Claim.class);
        String email = "test@example.com";
        User user = new User("1", "username", email, "hashedPassword");

        Mockito.when(authComponent.decodeJwt(refreshTokenValue)).thenReturn(decodedJWT);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        Mockito.when(emailClaim.asString()).thenReturn(email);
        Mockito.when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        Mockito.when(refreshClaim.asBoolean()).thenReturn(true);
        Mockito.when(userPersistentRepository.findByEmail(email)).thenReturn(user);

        Credential token = new Credential("tokenValue", "Bearer", null, false);
        Credential refreshToken = new Credential("refreshTokenValue", "Bearer", null, true);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
        Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(refreshToken);

        // WHEN
        AuthResponse response = authService.login(refreshTokenValue);

        // THEN
        assertNotNull(response);
        assertEquals(token.getToken(), response.getAccessToken());
        assertEquals(refreshToken.getToken(), response.getRefreshToken());
    }


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text
2025-10-06 13:51:18.721 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 13:51:19.068 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 13:51:19.071 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 13:51:19.071 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 13:51:19.071 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 14:46:51.539 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 14:46:51.541 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 14:46:51.541 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,28] ';' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,56] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[362,1] illegal start of type
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,28] ';' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,56] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[362,1] illegal start of type
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 14:46:51.543 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:46:51.932 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 14:46:51.935 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 14:46:51.935 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 14:46:51.935 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 14:46:55.441 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 14:46:55.441 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 14:46:55.441 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,28] ';' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,56] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[362,1] illegal start of type
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,28] ';' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,56] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[362,1] illegal start of type
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 14:46:55.443 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:46:55.924 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 14:46:55.926 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 14:46:55.927 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 14:46:55.927 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 14:46:59.047 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 14:46:59.047 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 14:46:59.047 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,28] ';' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,56] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[362,1] illegal start of type
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,28] ';' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,56] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[362,1] illegal start of type
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 14:46:59.048 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:46:59.659 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 14:46:59.662 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 14:46:59.662 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 14:46:59.662 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 14:47:02.801 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 14:47:02.802 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 14:47:02.802 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:116)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,28] ';' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,56] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[362,1] illegal start of type
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[120,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[121,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[122,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[123,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[124,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[125,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[126,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[127,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[128,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[129,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,19] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[132,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[134,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[136,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[137,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,55] ')' or ',' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[231,83] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[233,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[234,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[235,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[236,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[237,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[238,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[239,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[240,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[241,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[242,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[243,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[244,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[246,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[248,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,1] illegal start of type
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[249,35] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,28] ';' expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[344,56] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[346,32] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[347,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[348,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[349,45] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[350,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[351,41] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[352,51] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[353,55] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[354,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[355,29] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[356,33] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[357,39] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[359,17] not a statement
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,1] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,8] illegal start of expression
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[361,48] <identifier> expected
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-9496419409204322640/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[362,1] illegal start of type
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 14:47:02.804 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:120)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 14:47:03.521 ERROR [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:49)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - AI ERROR - did not generate response
java.lang.IllegalStateException: channel not registered to an event loop
	at io.netty.channel.AbstractChannel.eventLoop(AbstractChannel.java:163)
	at com.azure.core.http.netty.implementation.NettyUtility.closeConnection(NettyUtility.java:79)
	at com.azure.core.http.netty.implementation.NettyAsyncHttpResponse.close(NettyAsyncHttpResponse.java:116)
	at com.azure.core.http.policy.RetryPolicy.attemptSync(RetryPolicy.java:249)
	at com.azure.core.http.policy.RetryPolicy.processSync(RetryPolicy.java:161)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersPolicy.processSync(AddHeadersPolicy.java:66)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.AddHeadersFromContextPolicy.processSync(AddHeadersFromContextPolicy.java:67)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.RequestIdPolicy.processSync(RequestIdPolicy.java:77)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.policy.HttpPipelineSyncPolicy.processSync(HttpPipelineSyncPolicy.java:51)
	at com.azure.core.http.policy.UserAgentPolicy.processSync(UserAgentPolicy.java:174)
	at com.azure.core.http.HttpPipelineNextSyncPolicy.processSync(HttpPipelineNextSyncPolicy.java:53)
	at com.azure.core.http.HttpPipeline.sendSync(HttpPipeline.java:138)
	at com.azure.core.implementation.http.rest.SyncRestProxy.send(SyncRestProxy.java:62)
	at com.azure.core.implementation.http.rest.SyncRestProxy.invoke(SyncRestProxy.java:83)
	at com.azure.core.implementation.http.rest.RestProxyBase.invoke(RestProxyBase.java:124)
	at com.azure.core.http.rest.RestProxy.invoke(RestProxy.java:95)
	at jdk.proxy1/jdk.proxy1.$Proxy87.getChatCompletionsSync(Unknown Source)
	at com.azure.ai.openai.implementation.OpenAIClientImpl.getChatCompletionsWithResponse(OpenAIClientImpl.java:1972)
	at com.azure.ai.openai.OpenAIClient.getChatCompletionsWithResponse(OpenAIClient.java:350)
	at com.azure.ai.openai.OpenAIClient.getChatCompletions(OpenAIClient.java:760)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.lambda$doChat$0(AzureOpenAiChatModel.java:208)
	at dev.langchain4j.internal.ExceptionMapper.withExceptionMapper(ExceptionMapper.java:29)
	at dev.langchain4j.model.azure.AzureOpenAiChatModel.doChat(AzureOpenAiChatModel.java:207)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:46)
	at dev.langchain4j.model.chat.ChatModel.chat(ChatModel.java:92)
	at io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:44)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:122)
	at io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:79)
	at io.github.adamw7.orchestrator.generator.persistence.PersistingGenerator.create(PersistingGenerator.java:32)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:64)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.createInternal(RetryUntilTestSuccessGenerator.java:124)
	at io.github.adamw7.orchestrator.generator.RetryUntilTestSuccessGenerator.create(RetryUntilTestSuccessGenerator.java:53)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.createInternal(SpeedUpSlowTestsGenerator.java:30)
	at io.github.adamw7.orchestrator.generator.SpeedUpSlowTestsGenerator.create(SpeedUpSlowTestsGenerator.java:22)
	at io.github.adamw7.testing.generator.persistence.CodeRevertingGenerator.create(CodeRevertingGenerator.java:43)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
	at java.base/java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
	at java.base/java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
	at java.base/java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
	at io.github.adamw7.testing.steps.BaseClassContainerGeneratorStep.filterAndGenerateClasses(BaseClassContainerGeneratorStep.java:27)
	at io.github.adamw7.testing.steps.GenerateUnitTestStep.process(GenerateUnitTestStep.java:35)
	at io.github.adamw7.testing.steps.ProcessingPipeline.execute(ProcessingPipeline.java:17)
	at io.github.adamw7.testing.engine.UnitTestingEngine.execute(UnitTestingEngine.java:73)
	at io.github.adamw7.testing.cases.TestCase.execute(TestCase.java:21)
	at io.github.adamw7.testing.services.OrchestrationClientFacadeImpl.execute(OrchestrationClientFacadeImpl.java:15)
	at io.github.adamw7.testing.UnitTesting$1.run(UnitTesting.java:43)
	at org.springframework.boot.SpringApplication.lambda$callRunner$5(SpringApplication.java:788)
	at org.springframework.util.function.ThrowingConsumer$1.acceptWithException(ThrowingConsumer.java:82)
	at org.springframework.util.function.ThrowingConsumer.accept(ThrowingConsumer.java:60)
	at org.springframework.util.function.ThrowingConsumer$1.accept(ThrowingConsumer.java:86)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:796)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:787)
	at org.springframework.boot.SpringApplication.lambda$callRunners$3(SpringApplication.java:772)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.SortedOps$SizedRefSortingSink.end(SortedOps.java:357)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:510)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:772)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:325)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:144)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:58)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:46)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1461)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:563)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:144)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:110)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:225)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:152)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:155)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:111)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:260)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:159)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$12(ClassBasedTestDescriptor.java:421)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:426)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$13(ClassBasedTestDescriptor.java:420)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1708)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:420)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$8(ClassBasedTestDescriptor.java:331)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:330)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$6(ClassBasedTestDescriptor.java:319)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$7(ClassBasedTestDescriptor.java:318)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:27)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:128)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:70)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:129)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:96)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:161)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:147)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:145)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:144)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:101)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.executeEngine(EngineExecutionOrchestrator.java:230)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.failOrExecuteEngine(EngineExecutionOrchestrator.java:204)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:172)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:101)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:64)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:150)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:63)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:109)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:91)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.InterceptingLauncher.lambda$execute$1(InterceptingLauncher.java:39)
	at org.junit.platform.launcher.core.ClasspathAlignmentCheckingLauncherInterceptor.intercept(ClasspathAlignmentCheckingLauncherInterceptor.java:25)
	at org.junit.platform.launcher.core.InterceptingLauncher.execute(InterceptingLauncher.java:38)
	at org.junit.platform.launcher.core.DelegatingLauncher.execute(DelegatingLauncher.java:47)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:66)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:66)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:231)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
2025-10-06 14:47:03.524 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:124)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Failed to generate code
2025-10-06 14:47:03.524 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 14:47:03.524 WARN [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:84)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - No code to be used! Generated code is empty
2025-10-06 16:01:19.199 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:40)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-10-06 16:01:19.201 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:63)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generating code...
2025-10-06 16:01:19.203 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:114)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[49,27] constructor Credential in class com.bestpractice.api.domain.model.Credential cannot be applied to given types;
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[50,29] constructor Credential in class com.bestpractice.api.domain.model.Credential cannot be applied to given types;
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[95,67] no suitable method found for thenReturn(()->"email[...].com")
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[96,65] no suitable method found for thenReturn(()->true)
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[117,67] no suitable method found for thenReturn(()->"email[...].com")
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,65] no suitable method found for thenReturn(()->false)
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,67] no suitable method found for thenReturn(()->"nonex[...].com")
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,65] no suitable method found for thenReturn(()->true)
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure: Compilation failure: 
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[49,27] constructor Credential in class com.bestpractice.api.domain.model.Credential cannot be applied to given types;
[ERROR]   required: java.lang.String,java.lang.String,java.util.Date,boolean
[ERROR]   found:    java.lang.String,java.lang.String,java.util.Date
[ERROR]   reason: actual and formal argument lists differ in length
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[50,29] constructor Credential in class com.bestpractice.api.domain.model.Credential cannot be applied to given types;
[ERROR]   required: java.lang.String,java.lang.String,java.util.Date,boolean
[ERROR]   found:    java.lang.String,java.lang.String,java.util.Date
[ERROR]   reason: actual and formal argument lists differ in length
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[95,67] no suitable method found for thenReturn(()->"email[...].com")
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim,com.auth0.jwt.interfaces.Claim...) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[96,65] no suitable method found for thenReturn(()->true)
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim,com.auth0.jwt.interfaces.Claim...) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[117,67] no suitable method found for thenReturn(()->"email[...].com")
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim,com.auth0.jwt.interfaces.Claim...) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[118,65] no suitable method found for thenReturn(()->false)
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim,com.auth0.jwt.interfaces.Claim...) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[130,67] no suitable method found for thenReturn(()->"nonex[...].com")
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim,com.auth0.jwt.interfaces.Claim...) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR] /C:/Users/mrckw/AppData/Local/Temp/codeai-test-6611502173495608718/src/test/java/com/bestpractice/api/domain/service/AuthServiceImplGeneratedAiTests.java:[131,65] no suitable method found for thenReturn(()->true)
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR]     method org.mockito.stubbing.OngoingStubbing.thenReturn(com.auth0.jwt.interfaces.Claim,com.auth0.jwt.interfaces.Claim...) is not applicable
[ERROR]       (argument mismatch; com.auth0.jwt.interfaces.Claim is not a functional interface
[ERROR]           multiple non-overriding abstract methods found in interface com.auth0.jwt.interfaces.Claim)
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-10-06 16:01:19.203 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:118)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 1
2025-10-06 16:01:25.172 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5594, outputTokenCount = 1024, totalTokenCount = 6618 }
2025-10-06 16:01:25.174 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:118)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generate code iteration # 2
2025-10-06 16:01:27.738 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 6630, outputTokenCount = 300, totalTokenCount = 6930 }
2025-10-06 16:01:27.738 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:81)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 16:01:27.738 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:87)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Generated code:
package com.bestpractice.api.domain.service;

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
        testUser = new User("id123", "username", "email@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date(), false);
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date(), true);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("email@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals(tokenCredential.getTokenType(), response.getTokenType());
        assertEquals(tokenCredential.getToken(), response.getToken());
        assertEquals(refreshCredential.getToken(), response.getRefreshToken());
        assertEquals(tokenCredential.getExp(), response.getExpiresAt());
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
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("email@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("email@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals(tokenCredential.getTokenType(), response.getTokenType());
        assertEquals(tokenCredential.getToken(), response.getToken());
        assertEquals(refreshCredential.getToken(), response.getRefreshToken());
        assertEquals(tokenCredential.getExp(), response.getExpiresAt());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("email@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenWithNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("nonexistent@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("refreshTokenValue"));
    }
}
2025-10-06 16:01:27.739 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:88)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refining code...
2025-10-06 16:01:27.740 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:90)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Done
2025-10-06 16:01:27.741 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:91)] [{conversationName=com.bestpractice.api.domain.service.AuthServiceImplGeneratedAiTests.java}] - Refined generated code:
package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        testUser = new User("id123", "username", "email@example.com", "hashedPassword");
        tokenCredential = new Credential("Bearer", "tokenValue", new Date(), false);
        refreshCredential = new Credential("Bearer", "refreshTokenValue", new Date(), true);
    }

    @Test
    public void givenValidEmailAndPassword_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("plainPassword", "hashedPassword")).thenReturn(true);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("email@example.com", "plainPassword");

        // THEN
        assertNotNull(response);
        assertEquals(tokenCredential.getTokenType(), response.getTokenType());
        assertEquals(tokenCredential.getToken(), response.getToken());
        assertEquals(refreshCredential.getToken(), response.getRefreshToken());
        assertEquals(tokenCredential.getExp(), response.getExpiresAt());
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
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);
        when(encryptionComponent.matchedPassword("wrongPassword", "hashedPassword")).thenReturn(false);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("email@example.com", "wrongPassword"));
    }

    @Test
    public void givenValidRefreshToken_whenLogin_thenReturnAuthResponse() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("email@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), false)).thenReturn(tokenCredential);
        when(authComponent.generateJwt(testUser.getId(), testUser.getEmail(), true)).thenReturn(refreshCredential);

        // WHEN
        AuthResponse response = authServiceImpl.login("refreshTokenValue");

        // THEN
        assertNotNull(response);
        assertEquals(tokenCredential.getTokenType(), response.getTokenType());
        assertEquals(tokenCredential.getToken(), response.getToken());
        assertEquals(refreshCredential.getToken(), response.getRefreshToken());
        assertEquals(tokenCredential.getExp(), response.getExpiresAt());
    }

    @Test
    public void givenInvalidRefreshToken_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("email@example.com");
        when(refreshClaim.asBoolean()).thenReturn(false);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("invalidRefreshToken")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("email@example.com")).thenReturn(testUser);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("invalidRefreshToken"));
    }

    @Test
    public void givenRefreshTokenWithNonExistingUser_whenLogin_thenThrowUnAuthorized() {
        // GIVEN
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Claim emailClaim = mock(Claim.class);
        Claim refreshClaim = mock(Claim.class);
        when(emailClaim.asString()).thenReturn("nonexistent@example.com");
        when(refreshClaim.asBoolean()).thenReturn(true);
        when(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey)).thenReturn(emailClaim);
        when(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey)).thenReturn(refreshClaim);
        when(authComponent.decodeJwt("refreshTokenValue")).thenReturn(decodedJWT);
        when(userPersistentRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authServiceImpl.login("refreshTokenValue"));
    }
}
*/
