package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthComponentGeneratedAiTests {

    private AuthComponent authComponent;
    private CredentialProperty credentialProperty;
    private static final String SECRET = "secret";

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setProvider("provider");
        credentialProperty.setHmacSecret(SECRET);
        credentialProperty.setExpiresHourStr("1");
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void decodeJwt_success() {
        // GIVEN
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Map<String, Object> header = new HashMap<>();
        header.put("alg", algorithm.getName());
        header.put("typ", "JWT");
        String token = JWT.create()
                .withIssuer("provider")
                .withAudience("any")
                .withIssuedAt(new Date())
                .withHeader(header)
                .withClaim(AuthComponent.ClaimUserIdKey, "123")
                .withClaim(AuthComponent.ClaimUserEmailKey, "email@example.com")
                .withSubject("123")
                .sign(algorithm);

        // WHEN
        DecodedJWT decoded = authComponent.decodeJwt(token);

        // THEN
        assertThat(decoded).isNotNull();
        assertThat(decoded.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo("123");
        assertThat(decoded.getClaim(AuthComponent.ClaimUserEmailKey).asString()).isEqualTo("email@example.com");
        assertThat(decoded.getSubject()).isEqualTo("123");
    }

    @Test
    void decodeJwt_expired() {
        // GIVEN
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Map<String, Object> header = new HashMap<>();
        header.put("alg", algorithm.getName());
        header.put("typ", "JWT");
        Date past = new Date(System.currentTimeMillis() - 1000);
        String token = JWT.create()
                .withIssuer("provider")
                .withAudience("any")
                .withIssuedAt(past)
                .withExpiresAt(past)
                .withHeader(header)
                .withClaim(AuthComponent.ClaimUserIdKey, "123")
                .withClaim(AuthComponent.ClaimUserEmailKey, "email@example.com")
                .withSubject("123")
                .sign(algorithm);

        // WHEN & THEN
        UnAuthorized ex = assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
        assertThat(ex.getMessage()).isEqualTo("Token is expired time");
    }

    @Test
    void decodeJwt_invalidSignature() {
        // GIVEN
        Algorithm wrongAlgorithm = Algorithm.HMAC256("wrong");
        Map<String, Object> header = new HashMap<>();
        header.put("alg", wrongAlgorithm.getName());
        header.put("typ", "JWT");
        String token = JWT.create()
                .withIssuer("provider")
                .withAudience("any")
                .withIssuedAt(new Date())
                .withHeader(header)
                .withClaim(AuthComponent.ClaimUserIdKey, "123")
                .withClaim(AuthComponent.ClaimUserEmailKey, "email@example.com")
                .withSubject("123")
                .sign(wrongAlgorithm);

        // WHEN & THEN
        InternalServerError ex = assertThrows(InternalServerError.class, () -> authComponent.decodeJwt(token));
        assertThat(ex.getMessage()).isEqualTo("Unknown signature secret key");
    }

    @Test
    void decodeJwt_invalidToken() {
        // GIVEN
        String token = "invalid.token.value";

        // WHEN & THEN
        UnAuthorized ex = assertThrows(UnAuthorized.class, () -> authComponent.decodeJwt(token));
        assertThat(ex.getMessage()).isEqualTo("Invalid token");
    }

    @Test
    void generateJwt_nonRefresh_withExpiration() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);
        DecodedJWT decoded = JWT.decode(credential.getToken());

        // THEN
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.isRefresh()).isFalse();
        assertThat(credential.getExpiration()).isNotNull();
        assertThat(decoded.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isFalse();
        assertThat(decoded.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo(userId);
        assertThat(decoded.getClaim(AuthComponent.ClaimUserEmailKey).asString()).isEqualTo(email);
    }

    @Test
    void generateJwt_refresh() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = true;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);
        DecodedJWT decoded = JWT.decode(credential.getToken());

        // THEN
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.isRefresh()).isTrue();
        assertThat(credential.getExpiration()).isNull();
        assertThat(decoded.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isTrue();
    }

    @Test
    void generateJwt_noExpiration() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");
        authComponent = new AuthComponent(credentialProperty);
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertThat(credential.getExpiration()).isNull();
    }

    @Test
    void generateJwt_invalidExpires() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");
        authComponent = new AuthComponent(credentialProperty);
        String userId = "user123";
        String email = "user@example.com";
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, isRefresh);

        // THEN
        assertThat(credential.getExpiration()).isNull();
    }
}
