package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.mockito.Mockito;
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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthComponentGeneratedAiTests {

    @Mock
    private CredentialProperty credentialProperty;

    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        Mockito.when(credentialProperty.getHmacSecret()).thenReturn("testSecret");
        Mockito.when(credentialProperty.getProvider()).thenReturn("testProvider");
        Mockito.when(credentialProperty.convertToIntExpires()).thenReturn(1);
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void decodeJwt_validToken_returnsDecodedJwt() {
        // GIVEN
        String token = JWT.create()
                .withIssuer("testProvider")
                .sign(Algorithm.HMAC256("testSecret"));

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertThat(decodedJWT).isNotNull();
        assertThat(decodedJWT.getIssuer()).isEqualTo("testProvider");
    }

    @Test
    void decodeJwt_invalidSignature_throwsInternalServerError() {
        // GIVEN
        String token = JWT.create()
                .withIssuer("testProvider")
                .sign(Algorithm.HMAC256("wrongSecret"));

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Unknown signature secret key");
    }

    @Test
    void generateJwt_validInput_returnsCredential() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotBlank();
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.getExp()).isNotNull();
        assertThat(credential.isRefresh()).isFalse();
    }

    @Test
    void generateJwt_refreshToken_returnsCredentialWithRefreshTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.isRefresh()).isTrue();
        assertThat(credential.getExp()).isNull();
    }

    @Test
    void generateJwt_nullExpiresHour_returnsCredentialWithoutExpiration() {
        // GIVEN
        Mockito.when(credentialProperty.convertToIntExpires()).thenReturn(null);
        authComponent = new AuthComponent(credentialProperty);

        // WHEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getExp()).isNull();
    }

    @Test
    void decodeJwt_invalidTokenFormat_throwsUnAuthorized() {
        // GIVEN
        String invalidToken = "invalid.token.format";

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(invalidToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Invalid token");
    }
}
