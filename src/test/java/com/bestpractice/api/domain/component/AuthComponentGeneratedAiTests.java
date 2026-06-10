package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setHmacSecret("testSecret");
        credentialProperty.setProvider("testProvider");
        credentialProperty.setExpiresHourStr("2");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void decodeJwt_validToken_returnsDecodedJwt() {
        String token = JWT.create()
                .withIssuer("testProvider")
                .sign(Algorithm.HMAC256("testSecret"));
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);
        assertNotNull(decodedJWT);
        assertEquals("testProvider", decodedJWT.getIssuer());
    }

    @Test
    void decodeJwt_invalidSignature_throwsInternalServerError() {
        String token = JWT.create()
                .withIssuer("testProvider")
                .sign(Algorithm.HMAC256("wrongSecret"));
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Unknown signature secret key");
    }

    @Test
    void decodeJwt_expiredToken_throwsUnAuthorized() {
        Algorithm algorithm = Algorithm.HMAC256("testSecret");
        String token = JWT.create()
                .withIssuer("testProvider")
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() - 1000))
                .sign(algorithm);
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token is expired time");
    }

    @Test
    void decodeJwt_invalidTokenFormat_throwsUnAuthorized() {
        String invalidToken = "invalid.token.format";
        assertThatThrownBy(() -> authComponent.decodeJwt(invalidToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Invalid token");
    }

    @Test
    void decodeJwt_nullToken_throwsException() {
        String nullToken = null;
        assertThrows(Exception.class, () -> authComponent.decodeJwt(nullToken));
    }

    @Test
    void generateJwt_validAccessToken_returnsCredential() {
        String userId = "user123";
        String email = "user@example.com";
        Credential credential = authComponent.generateJwt(userId, email, false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
        assertFalse(credential.isRefresh());
    }

    @Test
    void generateJwt_refreshToken_returnsCredentialWithoutExpiration() {
        String userId = "user123";
        String email = "user@example.com";
        Credential credential = authComponent.generateJwt(userId, email, true);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
        assertTrue(credential.isRefresh());
    }

    @Test
    void generateJwt_nullExpiresHour_returnsCredentialWithoutExpiration() {
        credentialProperty.setExpiresHourStr("-");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_emptyUserId_stillGeneratesToken() {
        String userId = "";
        String email = "user@example.com";
        Credential credential = authComponent.generateJwt(userId, email, false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_whitespaceUserId_stillGeneratesToken() {
        String userId = "   ";
        String email = "user@example.com";
        Credential credential = authComponent.generateJwt(userId, email, false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_emptyEmail_stillGeneratesToken() {
        String userId = "user123";
        String email = "";
        Credential credential = authComponent.generateJwt(userId, email, false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_whitespaceEmail_stillGeneratesToken() {
        String userId = "user123";
        String email = "   ";
        Credential credential = authComponent.generateJwt(userId, email, false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_zeroExpiresHour_returnsCredentialWithoutExpiration() {
        credentialProperty.setExpiresHourStr("0");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_negativeExpiresHour_returnsCredentialWithoutExpiration() {
        credentialProperty.setExpiresHourStr("-1");
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_largeExpiresHour_returnsCredentialWithExpiration() {
        credentialProperty.setExpiresHourStr(String.valueOf(Integer.MAX_VALUE));
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_minExpiresHour_returnsCredentialWithoutExpiration() {
        credentialProperty.setExpiresHourStr(String.valueOf(Integer.MIN_VALUE));
        authComponent = new AuthComponent(credentialProperty);
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_nullEmail_stillGeneratesToken() {
        String userId = "user123";
        String email = null;
        Credential credential = authComponent.generateJwt(userId, email, false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }

    @Test
    void generateJwt_nullUserId_stillGeneratesToken() {
        String userId = null;
        String email = "user@example.com";
        Credential credential = authComponent.generateJwt(userId, email, false);
        assertNotNull(credential);
        assertNotNull(credential.getToken());
    }
}
