package com.bestpractice.api.domain.component;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthComponentGeneratedAiTests {

    @Mock
    private CredentialProperty credentialProperty;

    @InjectMocks
    private AuthComponent authComponent;

    @BeforeEach
    void setUp() {
        when(credentialProperty.getHmacSecret()).thenReturn("test-secret");
        when(credentialProperty.getProvider()).thenReturn("test-provider");
        when(credentialProperty.convertToIntExpires()).thenReturn(1);
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void givenValidToken_whenDecodeJwt_thenReturnDecodedJWT() {
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
    void givenInvalidSignature_whenDecodeJwt_thenThrowInternalServerError() {
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
    void givenExpiredToken_whenDecodeJwt_thenThrowUnAuthorized() {
        // GIVEN
        String token = JWT.create()
                .withIssuer("test-provider")
                .withExpiresAt(new Date(System.currentTimeMillis() - 1000))
                .sign(Algorithm.HMAC256("test-secret"));

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token is expired time");
    }

    @Test
    void givenInvalidToken_whenDecodeJwt_thenThrowUnAuthorized() {
        // GIVEN
        String invalidToken = "invalid.token.value";

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(invalidToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Invalid token");
    }

    @Test
    void givenValidInputs_whenGenerateJwt_thenReturnCredential() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotBlank();
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.getExpiredAt()).isNotNull();
        assertThat(credential.isRefresh()).isFalse();
    }

    @Test
    void givenRefreshTokenRequest_whenGenerateJwt_thenReturnRefreshCredential() {
        // GIVEN
        String userId = "user123";
        String email = "user@example.com";

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, true);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getToken()).isNotBlank();
        assertThat(credential.isRefresh()).isTrue();
        assertThat(credential.getExpiredAt()).isNull();
    }

    @Test
    void givenNullExpiresHour_whenGenerateJwt_thenReturnCredentialWithoutExpiration() {
        // GIVEN
        when(credentialProperty.convertToIntExpires()).thenReturn(null);
        authComponent = new AuthComponent(credentialProperty);

        // WHEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getExpiredAt()).isNull();
    }
}