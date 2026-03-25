package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuthComponentGeneratedAiTests {

    private CredentialProperty credentialProperty;
    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setHmacSecret("test-secret");
        credentialProperty.setProvider("test-provider");
        credentialProperty.setExpires("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void decodeJwt_validToken_shouldReturnDecodedJWT() {
        // GIVEN
        Algorithm algorithm = Algorithm.HMAC256("test-secret");
        String token = JWT.create()
                .withIssuer("test-provider")
                .sign(algorithm);

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertThat(decodedJWT).isNotNull();
        assertThat(decodedJWT.getIssuer()).isEqualTo("test-provider");
    }

    @Test
    void decodeJwt_invalidSignature_shouldThrowInternalServerError() {
        // GIVEN
        Algorithm wrongAlgorithm = Algorithm.HMAC256("wrong-secret");
        String token = JWT.create()
                .withIssuer("test-provider")
                .sign(wrongAlgorithm);

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Unknown signature secret key");
    }

    @Test
    void decodeJwt_expiredToken_shouldThrowUnAuthorized() {
        // GIVEN
        Algorithm algorithm = Algorithm.HMAC256("test-secret");
        Date pastDate = new Date(System.currentTimeMillis() - 10000);
        String token = JWT.create()
                .withIssuer("test-provider")
                .withExpiresAt(pastDate)
                .sign(algorithm);

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token is expired time");
    }

    @Test
    void generateJwt_validAccessToken_shouldReturnCredentialWithExpiration() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotEmpty();
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.getExp()).isNotNull();
        assertThat(credential.isRefresh()).isFalse();
    }

    @Test
    void generateJwt_refreshToken_shouldReturnCredentialWithoutExpiration() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotEmpty();
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.getExp()).isNull();
        assertThat(credential.isRefresh()).isTrue();
    }

    @Test
    void generateJwt_nullExpiration_shouldHandleGracefully() {
        // GIVEN
        CredentialProperty localCredentialProperty = new CredentialProperty();
        localCredentialProperty.setHmacSecret("test-secret");
        localCredentialProperty.setProvider("test-provider");
        localCredentialProperty.setExpires(null);
        AuthComponent localAuthComponent = new AuthComponent(localCredentialProperty);

        // WHEN
        Credential credential = localAuthComponent.generateJwt("user123", "user@example.com", false);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getExp()).isNull();
    }
}
