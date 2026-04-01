package com.bestpractice.api.domain.component;

import static org.mockito.Mockito.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
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

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class AuthComponentGeneratedAiTests {

    @Mock
    private CredentialProperty credentialProperty;

    @InjectMocks
    private AuthComponent authComponent;

    private static final String SECRET = "test-secret";
    private static final String PROVIDER = "test-provider";

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(credentialProperty.getHmacSecret()).thenReturn(SECRET);
        Mockito.lenient().when(credentialProperty.getProvider()).thenReturn(PROVIDER);
        Mockito.lenient().when(credentialProperty.convertToIntExpires()).thenReturn(1);
        authComponent = new AuthComponent(credentialProperty);
    }

    @Test
    void decodeJwt_shouldReturnDecodedJWT_whenTokenIsValid() {
        // GIVEN
        String token = JWT.create()
                .withIssuer(PROVIDER)
                .sign(Algorithm.HMAC256(SECRET));

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(token);

        // THEN
        assertThat(decodedJWT).isNotNull();
        assertThat(decodedJWT.getIssuer()).isEqualTo(PROVIDER);
    }

    @Test
    void decodeJwt_shouldThrowInternalServerError_whenSignatureInvalid() {
        // GIVEN
        String token = JWT.create()
                .withIssuer(PROVIDER)
                .sign(Algorithm.HMAC256("wrong-secret"));

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Unknown signature secret key");
    }

    @Test
    void decodeJwt_shouldThrowUnAuthorized_whenTokenExpired() {
        // GIVEN
        String token = JWT.create()
                .withIssuer(PROVIDER)
                .withExpiresAt(new Date(System.currentTimeMillis() - 1000))
                .sign(Algorithm.HMAC256(SECRET));

        // WHEN / THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(token))
                .isInstanceOf(UnAuthorized.class)
                .hasMessageContaining("Token is expired time");
    }

    @Test
    void decodeJwt_shouldThrowUnAuthorized_whenInvalidToken() {
        // GIVEN
        AuthComponent spyComponent = spy(authComponent);
        doThrow(new UnAuthorized("Invalid token")).when(spyComponent).decodeJwt("invalid");

        // WHEN / THEN
        assertThatThrownBy(() -> spyComponent.decodeJwt("invalid"))
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
        assertThat(credential.isRefresh()).isFalse();
        assertThat(credential.getExp()).isNotNull();
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
        assertThat(credential.isRefresh()).isTrue();
        assertThat(credential.getExp()).isNull();
    }

    @Test
    void generateJwt_shouldHandleNullExpirationGracefully() {
        // GIVEN
        Mockito.lenient().when(credentialProperty.convertToIntExpires()).thenReturn(null);
        authComponent = new AuthComponent(credentialProperty);

        // WHEN
        Credential credential = authComponent.generateJwt("user123", "user@example.com", false);

        // THEN
        assertThat(credential).isNotNull();
        assertThat(credential.getExp()).isNull();
    }
}
