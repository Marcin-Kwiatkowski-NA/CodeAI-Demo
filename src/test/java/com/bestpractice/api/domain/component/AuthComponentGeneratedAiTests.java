package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
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
    void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setProvider("testProvider");
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void givenValidToken_whenDecodeJwt_thenReturnDecodedJWT() {
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
    void givenExpiredToken_whenDecodeJwt_thenThrowUnAuthorized() throws InterruptedException {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");
        AuthComponent shortLivedAuthComponent = new AuthComponent(credentialProperty);
        Credential credential = shortLivedAuthComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();
        Thread.sleep(1000);

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> shortLivedAuthComponent.decodeJwt(token));
    }

    @Test
    void givenValidData_whenGenerateJwt_thenReturnCredentialWithExpectedClaims() {
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
        assertNotNull(credential.getExp());
    }

    @Test
    void givenRefreshTokenFlag_whenGenerateJwt_thenReturnCredentialWithRefreshClaimTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = true;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertNotNull(credential);
        assertTrue(credential.isRefresh());
        assertNull(credential.getExp());
    }

    @Test
    void givenInvalidSignature_whenDecodeJwt_thenThrowInternalServerError() {
        // GIVEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        String token = credential.getToken();
        String tamperedToken = token + "tamper";

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(tamperedToken));
    }
}
