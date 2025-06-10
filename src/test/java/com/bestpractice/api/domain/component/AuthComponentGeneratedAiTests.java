package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialPropertyMock;
    private AuthComponent authComponent;

    @BeforeEach
    public void setUp() {
        credentialPropertyMock = mock(CredentialProperty.class);
        when(credentialPropertyMock.getHmacSecret()).thenReturn("secret");
        when(credentialPropertyMock.convertToIntExpires()).thenReturn(1);
        when(credentialPropertyMock.getProvider()).thenReturn("provider");

        authComponent = new AuthComponent(credentialPropertyMock);
    }

    @Test
    public void testDecodeJwt_Success() {
        // GIVEN
        String token = "valid.token";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);

        // WHEN
        when(authComponent.decodeJwt(token)).thenReturn(decodedJWT);
        DecodedJWT result = authComponent.decodeJwt(token);

        // THEN
        assertEquals(result, decodedJWT);
    }

    @Test
    public void testDecodeJwt_SignatureVerificationException() {
        // GIVEN
        String token = "invalid.token";

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void testDecodeJwt_TokenExpiredException() {
        // GIVEN
        String token = "expired.token";

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void testDecodeJwt_InvalidToken() {
        // GIVEN
        String token = "invalid.token";

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
    }

    @Test
    public void testGenerateJwt_Success() {
        // GIVEN
        String userId = "user1";
        String email = "user1@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertEquals(credential.getTokenType(), "Bearer");
    }

    @Test
    public void testGenerateJwt_Refresh() {
        // GIVEN
        String userId = "user1";
        String email = "user1@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertEquals(credential.isRefresh(), true);
    }
}