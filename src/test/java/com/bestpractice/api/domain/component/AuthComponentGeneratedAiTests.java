package com.bestpractice.api.domain.component;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class AuthComponentGeneratedAiTests {

    @Mock
    private CredentialProperty credentialProperty;

    private AuthComponent authComponent;
    private Algorithm algorithm;

    private final String secret = "test-secret";
    private final String provider = "test-provider";
    private final String expiresHourStr = "1";
    private final String userId = "user-123";
    private final String userEmail = "user@example.com";

    @BeforeEach
    void setUp() {
        when(credentialProperty.getHmacSecret()).thenReturn(secret);
        when(credentialProperty.getProvider()).thenReturn(provider);
        when(credentialProperty.getExpiresHourStr()).thenReturn(expiresHourStr);
        when(credentialProperty.convertToIntExpires()).thenReturn(1);

        algorithm = Algorithm.HMAC256(secret);
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void decodeJwt_successfulDecoding_returnsDecodedJWT() {
        // GIVEN
        String token = JWT.create()
                .withIssuer(provider)
                .withAudience("any")
                .withIssuedAt(new Date())
                .withClaim(AuthComponent.ClaimUserIdKey, userId)
                .withClaim(AuthComponent.ClaimUserEmailKey, userEmail)
                .withSubject(userId)
                .sign(algorithm);

        // WHEN
        DecodedJWT decoded = authComponent.decodeJwt(token);

        // THEN
        assertThat(decoded).isNotNull();
        assertThat(decoded.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo(userId);
        assertThat(decoded.getClaim(AuthComponent.ClaimUserEmailKey).asString()).isEqualTo(userEmail);
        assertThat(decoded.getSubject()).isEqualTo(userId);
    }

    @Test
    void decodeJwt_expiredToken_throwsUnAuthorized() {
        // GIVEN
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -2);
        Date past = cal.getTime();

        String token = JWT.create()
                .withIssuer(provider)
                .withAudience("any")
                .withIssuedAt(new Date())
                .withExpiresAt(past)
                .withClaim(AuthComponent.ClaimUserIdKey, userId)
                .withClaim(AuthComponent.ClaimUserEmailKey, userEmail)
                .withSubject(userId)
                .sign(algorithm);

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token is expired time");
    }

    @Test
    void decodeJwt_invalidSignature_throwsInternalServerError() {
        // GIVEN
        Algorithm wrongAlgorithm = Algorithm.HMAC256("wrong-secret");
        String token = JWT.create()
                .withIssuer(provider)
                .withAudience("any")
                .withIssuedAt(new Date())
                .withClaim(AuthComponent.ClaimUserIdKey, userId)
                .withClaim(AuthComponent.ClaimUserEmailKey, userEmail)
                .withSubject(userId)
                .sign(wrongAlgorithm);

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Unknown signature secret key");
    }

    @Test
    void decodeJwt_invalidTokenFormat_throwsUnAuthorized() {
        // GIVEN
        String invalidToken = "invalid-token";

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(invalidToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Invalid token");
    }

    @Test
    void generateJwt_nonRefreshToken_setsExpirationAndClaims() {
        // GIVEN
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, userEmail, isRefresh);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotEmpty();
        assertThat(credential.getType()).isEqualTo("Bearer");
        assertThat(credential.getExpiration()).isNotNull();

        DecodedJWT decoded = JWT.decode(credential.getToken());
        assertThat(decoded.getClaim(AuthComponent.ClaimUserIdKey).asString()).isEqualTo(userId);
        assertThat(decoded.getClaim(AuthComponent.ClaimUserEmailKey).asString()).isEqualTo(userEmail);
        assertThat(decoded.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isFalse();
        assertThat(credential.isRefresh()).isFalse();
    }

    @Test
    void generateJwt_refreshToken_doesNotSetExpirationAndSetsRefreshClaim() {
        // GIVEN
        boolean isRefresh = true;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, userEmail, isRefresh);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotEmpty();
        assertThat(credential.getType()).isEqualTo("Bearer");
        assertThat(credential.getExpiration()).isNull();

        DecodedJWT decoded = JWT.decode(credential.getToken());
        assertThat(decoded.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isTrue();
        assertThat(credential.isRefresh()).isTrue();
    }

    @Test
    void generateJwt_noExpiresHour_doesNotSetExpiration() {
        // GIVEN
        when(credentialProperty.convertToIntExpires()).thenReturn(null);
        boolean isRefresh = false;

        // WHEN
        Credential credential = authComponent.generateJwt(userId, userEmail, isRefresh);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getExpiration()).isNull();

        DecodedJWT decoded = JWT.decode(credential.getToken());
        assertThat(decoded.getExpiresAt()).isNull();
    }
}
