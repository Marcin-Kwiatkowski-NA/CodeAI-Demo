package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * SECURITY-SENSITIVE: This test class validates JWT creation and decoding logic.
 * Ensure no real secrets or production keys are used in tests.
 */
@ExtendWith(MockitoExtension.class)
public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setHmacSecret("test-secret");
        credentialProperty.setProvider("test-provider");
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void decodeJwt_validToken_returnsDecodedJWT() {
        // GIVEN
        String token = JWT.create()
                .withIssuer("test-provider")
                .sign(Algorithm.HMAC256("test-secret"));

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertNotNull(decodedJWT);
        assertEquals("test-provider", decodedJWT.getIssuer());
    }

    @Test
    void decodeJwt_invalidSignature_throwsInternalServerError() {
        // GIVEN
        String token = JWT.create()
                .withIssuer("test-provider")
                .sign(Algorithm.HMAC256("wrong-secret"));

        // WHEN / THEN
        InternalServerError exception = assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
        assertTrue(exception.getMessage().contains("Unknown signature secret key"));
    }

    @Test
    void decodeJwt_invalidTokenFormat_throwsUnAuthorized() {
        // GIVEN
        String invalidToken = "invalid.token.value";

        // WHEN / THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(invalidToken));
        assertTrue(exception.getMessage().contains("Invalid token"));
    }

    @Test
    void decodeJwt_expiredToken_throwsUnAuthorized() {
        // GIVEN
        Algorithm algorithm = Algorithm.HMAC256("test-secret");
        String expiredToken = JWT.create()
                .withIssuer("test-provider")
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() - 1000))
                .sign(algorithm);

        // WHEN / THEN
        UnAuthorized exception = assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(expiredToken));
        assertTrue(exception.getMessage().contains("Token is expired time"));
    }

    @Test
    void generateJwt_validInput_returnsCredential() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertNotNull(credential);
        assertNotNull(credential.getToken());
        assertEquals("Bearer", credential.getTokenType());
        assertNotNull(credential.getExp());
        assertFalse(credential.isRefresh());
    }

    @Test
    void generateJwt_refreshToken_returnsCredentialWithRefreshTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertNotNull(credential);
        assertTrue(credential.isRefresh());
        assertEquals("Bearer", credential.getTokenType());
        assertEquals(null, credential.getExp());
    }

    @Test
    void generateJwt_nullExpiresHour_returnsCredentialWithoutExpiration() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");
        authComponent = new AuthComponent(credentialProperty);

        // WHEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);

        // THEN
        assertNotNull(credential);
        assertEquals(null, credential.getExp());
        assertNotNull(credential.getToken());
    }

    @Test
    void decodeJwt_unexpectedError_throwsInternalServerError() {
        // GIVEN
        AuthComponent faultyAuthComponent = new AuthComponent(credentialProperty) {
            @Override
            public DecodedJWT decodeJwt(String token) {
                throw new RuntimeException("Unexpected error occurred");
            }
        };

        // WHEN / THEN
        RuntimeException exception = assertThrows(RuntimeException.class, () -> faultyAuthComponent.decodeJwt("any-token"));
        assertTrue(exception.getMessage().contains("Unexpected error occurred"));
    }
}
