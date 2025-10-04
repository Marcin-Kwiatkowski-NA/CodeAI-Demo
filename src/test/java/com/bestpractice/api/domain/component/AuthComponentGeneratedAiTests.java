package com.bestpractice.api.domain.component;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    public void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setHmacSecret("testsecret");
        credentialProperty.setProvider("testprovider");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    public void givenValidToken_whenDecodeJwt_thenReturnDecodedJWT() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals("user123", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey).asString());
        assertEquals("user@example.com", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString());
    }

    @Test
    public void givenExpiredToken_whenDecodeJwt_thenThrowUnAuthorized() throws InterruptedException {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();
        Thread.sleep(1000);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenInvalidSignatureToken_whenDecodeJwt_thenThrowInternalServerError() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken() + "tampered";

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenValidData_whenGenerateJwt_thenReturnCredentialWithExpectedClaims() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertNotNull(credential);
        assertEquals("Bearer", credential.getTokenType());
        assertFalse(credential.isRefresh());
        assertNotNull(credential.getExpiresAt());
        assertTrue(credential.getExpiresAt().after(new Date()));
    }

    @Test
    public void givenRefreshTokenFlag_whenGenerateJwt_thenReturnCredentialWithRefreshClaimTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = true;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertNotNull(credential);
        assertTrue(credential.isRefresh());
        assertNull(credential.getExpiresAt());
    }
}