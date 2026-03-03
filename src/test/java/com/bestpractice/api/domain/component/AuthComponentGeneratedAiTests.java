package com.bestpractice.api.domain.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthComponentGeneratedAiTests {

    @Mock
    private CredentialProperty credentialProperty;

    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        // Default mock behavior for HMAC secret and provider
        org.mockito.Mockito.when(credentialProperty.getHmacSecret()).thenReturn("test-secret");
        org.mockito.Mockito.when(credentialProperty.getProvider()).thenReturn("test-provider");
        // Default expiresHourStr to "1" unless overridden in specific tests
        org.mockito.Mockito.when(credentialProperty.getExpiresHourStr()).thenReturn("1");
        org.mockito.Mockito.when(credentialProperty.convertToIntExpires()).thenReturn(1);
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void testDecodeJwtSuccess() {
        // GIVEN
        Algorithm algorithm = Algorithm.HMAC256("test-secret");
        String token = JWT.create()
                .withIssuer("test-provider")
                .withSubject("user123")
                .withClaim(AuthComponent.ClaimUserIdKey, "user123")
                .withClaim(AuthComponent.ClaimUserEmailKey, "user@example.com")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600_000))
                .sign(algorithm);

        // WHEN
        DecodedJWT decoded = authComponent.decodeJwt(token);

        // THEN
        assertThat(decoded).isNotNull();
        assertThat(decoded.getSubject()).isEqualTo("user123");
        assertThat(decoded.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo("user123");
    }

    @Test
    void testDecodeJwtExpired() {
        // GIVEN
        Algorithm algorithm = Algorithm.HMAC256("test-secret");
        String token = JWT.create()
                .withIssuer("test-provider")
                .withSubject("user123")
                .withExpiresAt(new Date(System.currentTimeMillis() - 1000))
                .sign(algorithm);

        // WHEN & THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token is expired time");
    }

    @Test
    void testDecodeJwtWrongSecret() {
        // GIVEN
        Algorithm correctAlgorithm = Algorithm.HMAC256("test-secret");
        Algorithm wrongAlgorithm = Algorithm.HMAC256("wrong-secret");
        String token = JWT.create()
                .withIssuer("test-provider")
                .withSubject("user123")
                .sign(wrongAlgorithm);

        // WHEN & THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Unknown signature secret key");
    }

    @Test
    void testDecodeJwtInvalidToken() {
        // GIVEN
        String token = "invalid.token.string";

        // WHEN & THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Invalid token");
    }

    @Test
    void testGenerateJwtNonRefreshWithExpires() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.isRefresh()).isFalse();
        assertThat(credential.getExp()).isNotNull();

        DecodedJWT decoded = JWT.decode(credential.getToken());
        assertThat(decoded.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo(userId);
        assertThat(decoded.getClaim(AuthComponent.ClaimUserEmailKey).asString()).isEqualTo(email);
        assertThat(decoded.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isFalse();
    }

    @Test
    void testGenerateJwtRefresh() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = true;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.isRefresh()).isTrue();
        assertThat(credential.getExp()).isNull();

        DecodedJWT decoded = JWT.decode(credential.getToken());
        assertThat(decoded.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isTrue();
    }

    @Test
    void testGenerateJwtNonRefreshNoExpires() {
        // GIVEN
        org.mockito.Mockito.when(credentialProperty.getExpiresHourStr()).thenReturn("-");
        org.mockito.Mockito.when(credentialProperty.convertToIntExpires()).thenReturn(null);

        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getExp()).isNull();
    }
}
