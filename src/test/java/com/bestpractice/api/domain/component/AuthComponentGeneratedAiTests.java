package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthComponentGeneratedAiTests {

    @Mock
    private CredentialProperty credentialProperty;

    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        // Default mock configuration
        when(credentialProperty.getHmacSecret()).thenReturn("secret");
        when(credentialProperty.getProvider()).thenReturn("testProvider");
        when(credentialProperty.convertToIntExpires()).thenReturn(1);
    }

    @Test
    void testGenerateJwtWithRefreshToken() {
        // GIVEN
        authComponent = new AuthComponent(credentialProperty);
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = true;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotEmpty();
        assertThat(credential.getType()).isEqualTo("Bearer");
        assertThat(credential.getExpiration()).isNull();
        assertThat(credential.isRefresh()).isTrue();

        DecodedJWT decoded = JWT.decode(credential.getToken());
        assertThat(decoded.getHeaderClaim("alg").asString()).isEqualTo("HS256");
        assertThat(decoded.getHeaderClaim("typ").asString()).isEqualTo("JWT");
        assertThat(decoded.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo(userId);
        assertThat(decoded.getClaim(AuthComponent.ClaimUserEmailKey).asString()).isEqualTo(email);
        assertThat(decoded.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isTrue();
    }

    @Test
    void testGenerateJwtWithAccessToken() {
        // GIVEN
        authComponent = new AuthComponent(credentialProperty);
        String userId = "user456";
        String email = "another@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotEmpty();
        assertThat(credential.getType()).isEqualTo("Bearer");
        assertThat(credential.getExpiration()).isNotNull();
        assertThat(credential.isRefresh()).isFalse();

        DecodedJWT decoded = JWT.decode(credential.getToken());
        assertThat(decoded.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isFalse();
        assertThat(decoded.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo(userId);
        assertThat(decoded.getClaim(AuthComponent.ClaimUserEmailKey).asString()).isEqualTo(email);
    }

    @Test
    void testDecodeJwtValidToken() {
        // GIVEN
        authComponent = new AuthComponent(credentialProperty);
        String userId = "validUser";
        String email = "valid@example.com";
        Credential credential = authComponent.generateJwt(userId, email, false);
        String token = credential.getToken();

        // WHEN
        DecodedJWT decoded = authComponent.decodeJwt(token);

        // THEN
        assertThat(decoded).isNotNull();
        assertThat(decoded.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo(userId);
        assertThat(decoded.getClaim(AuthComponent.ClaimUserEmailKey).asString()).isEqualTo(email);
        assertThat(decoded.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isFalse();
    }

    @Test
    void testDecodeJwtSignatureVerificationException() {
        // GIVEN
        authComponent = new AuthComponent(credentialProperty);
        Algorithm wrongAlgorithm = Algorithm.HMAC256("wrongSecret");
        String token = JWT.create()
                .withIssuer("testProvider")
                .withClaim(AuthComponent.ClaimUserIdKey, "user")
                .sign(wrongAlgorithm);

        // WHEN
        Throwable thrown = assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));

        // THEN
        assertThat(thrown.getMessage()).isEqualTo("Unknown signature secret key");
    }

    @Test
    void testDecodeJwtTokenExpiredException() {
        // GIVEN
        authComponent = new AuthComponent(credentialProperty);
        Algorithm algorithm = Algorithm.HMAC256(credentialProperty.getHmacSecret());
        Date past = new Date(System.currentTimeMillis() - 3600_000); // 1 hour ago
        String token = JWT.create()
                .withIssuer("testProvider")
                .withExpiresAt(past)
                .withClaim(AuthComponent.ClaimUserIdKey, "user")
                .sign(algorithm);

        // WHEN
        Throwable thrown = assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));

        // THEN
        assertThat(thrown.getMessage()).isEqualTo("Token is expired");
    }

    @Test
    void testDecodeJwtInvalidToken() {
        // GIVEN
        authComponent = new AuthComponent(credentialProperty);
        String malformedToken = "not.a.valid.jwt";

        // WHEN
        Throwable thrown = assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(malformedToken));

        // THEN
        assertThat(thrown.getMessage()).isEqualTo("Invalid token");
    }

    @Test
    void testGenerateJwtWhenExpiresHourIsNull() {
        // GIVEN
        when(credentialProperty.convertToIntExpires()).thenReturn(null);
        authComponent = new AuthComponent(credentialProperty);
        String userId = "user789";
        String email = "noexpire@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertThat(credential.getExpiration()).isNull();
    }
}
