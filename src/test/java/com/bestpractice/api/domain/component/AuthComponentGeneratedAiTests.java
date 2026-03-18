package com.bestpractice.api.domain.component;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

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
public class AuthComponentGeneratedAiTests {

    @Mock
    private CredentialProperty credentialProperty;

    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        // GIVEN
        Mockito.lenient().when(credentialProperty.getHmacSecret()).thenReturn("test-secret");
        Mockito.lenient().when(credentialProperty.getProvider()).thenReturn("test-provider");
        Mockito.lenient().when(credentialProperty.convertToIntExpires()).thenReturn(1);

        // WHEN
        authComponent = new AuthComponent(credentialProperty);

        // THEN
        assertThat(authComponent).isNotNull();
    }

    @Test
    void decodeJwt_validToken_shouldReturnDecodedJWT() {
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
    void decodeJwt_invalidSignature_shouldThrowInternalServerError() {
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
    void generateJwt_validInput_shouldReturnCredentialWithExpectedClaims() {
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
    void generateJwt_refreshToken_shouldContainRefreshClaimTrue() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);
        DecodedJWT decodedJWT = JWT.decode(credential.getToken());

        // THEN
        assertThat(decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean()).isTrue();
        assertThat(credential.isRefresh()).isTrue();
    }

    @Test
    void generateJwt_expirationShouldBeNullForRefreshToken() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertThat(credential.getExp()).isNull();
    }

    @Test
    void generateJwt_expirationShouldBeSetForNonRefreshToken() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertThat(credential.getExp()).isNotNull();
        assertThat(credential.getExp()).isAfter(new Date(System.currentTimeMillis()));
    }

    @Test
    void decodeJwt_invalidTokenFormat_shouldThrowUnAuthorized() {
        // GIVEN
        String invalidToken = "invalid.token.format";

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(invalidToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Invalid token");
    }
}