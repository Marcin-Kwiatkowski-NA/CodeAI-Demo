package com.bestpractice.api.domain.component;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthComponentGeneratedAiTests {

    @Mock
    private CredentialProperty credentialProperty;

    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        lenient().when(credentialProperty.getHmacSecret()).thenReturn("test-secret");
        lenient().when(credentialProperty.getProvider()).thenReturn("test-provider");
        lenient().when(credentialProperty.convertToIntExpires()).thenReturn(1);
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void decodeJwt_shouldReturnDecodedJWT_whenTokenIsValid() {
        // GIVEN
        String token = JWT.create()
                .withIssuer("test-provider")
                .sign(Algorithm.HMAC256("test-secret"));

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertThat(decodedJWT).isNotNull();
        assertThat(decodedJWT.getIssuer()).isEqualTo("test-provider");
    }

    @Test
    void decodeJwt_shouldThrowInternalServerError_whenSignatureInvalid() {
        // GIVEN
        String token = JWT.create()
                .withIssuer("test-provider")
                .sign(Algorithm.HMAC256("wrong-secret"));

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Unknown signature secret key");
    }

    @Test
    void decodeJwt_shouldThrowUnAuthorized_whenTokenIsMalformed() {
        // GIVEN
        String invalidToken = "invalid.token.value";

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(invalidToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Invalid token");
    }

    @Test
    void generateJwt_shouldGenerateAccessToken_whenNotRefresh() {
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
    void generateJwt_shouldGenerateRefreshToken_whenIsRefreshTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotBlank();
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.getExp()).isNull();
        assertThat(credential.isRefresh()).isTrue();
    }

    @Test
    void generateJwt_shouldIncludeClaimsCorrectly() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);
        DecodedJWT decodedJWT = authComponent.decodeJwt(credential.getToken());

        // THEN
        assertThat(decodedJWT.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo(userId);
        assertThat(decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString()).isEqualTo(email);
        assertThat(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isFalse();
    }

    @Test
    void generateJwt_shouldHandleNullExpirationGracefully() {
        // GIVEN
        lenient().when(credentialProperty.convertToIntExpires()).thenReturn(null);
        authComponent = new AuthComponent(credentialProperty);

        // WHEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);

        // THEN
        assertThat(credential.getExp()).isNull();
        assertThat(credential.getToken()).isNotBlank();
    }
}