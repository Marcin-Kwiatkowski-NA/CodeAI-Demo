package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import com.auth0.jwt.JWT;
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
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setProvider("testProvider");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    public void givenValidToken_whenDecodeJwt_thenReturnsDecodedJWT() {
        // GIVEN
        String token = JWT.create()
                .withIssuer(credentialProperty.getProvider())
                .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(credentialProperty.getHmacSecret()));

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals(credentialProperty.getProvider(), decodedJWT.getIssuer());
    }

    @Test
    public void givenExpiredToken_whenDecodeJwt_thenThrowsUnAuthorized() {
        // GIVEN
        Date pastDate = new Date(System.currentTimeMillis() - 1000);
        String token = JWT.create()
                .withIssuer(credentialProperty.getProvider())
                .withExpiresAt(pastDate)
                .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(credentialProperty.getHmacSecret()));

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenInvalidSignature_whenDecodeJwt_thenThrowsInternalServerError() {
        // GIVEN
        String token = JWT.create()
                .withIssuer(credentialProperty.getProvider())
                .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256("wrongSecret"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void givenValidData_whenGenerateJwt_thenReturnsCredentialWithToken() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertNotNull(credential);
        assertNotNull(credential.getToken());
        assertEquals("Bearer", credential.getTokenType());
        assertFalse(credential.isRefresh());
        assertNotNull(credential.getExp());
    }

    @Test
    public void givenRefreshTokenRequest_whenGenerateJwt_thenReturnsCredentialWithRefreshFlagTrue() {
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
}
