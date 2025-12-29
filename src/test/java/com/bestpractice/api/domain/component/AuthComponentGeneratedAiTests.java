package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthComponentGeneratedAiTests {

    @Mock
    private CredentialProperty credentialProperty;

    @InjectMocks
    private AuthComponent authComponent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidToken_ShouldDecodeSuccessfully() {
        // GIVEN
        String validToken = "valid.jwt.token.here";
        when(credentialProperty.getHmacSecret()).thenReturn("secret-key");
        when(credentialProperty.getProvider()).thenReturn("test-provider");
        when(credentialProperty.convertToIntExpires()).thenReturn(24);

        // WHEN
        DecodedJWT decodedJWT = authComponent.decodeJwt(validToken);

        // THEN
        assertThat(decodedJWT).isNotNull();
        assertThat(decodedJWT.getSubject()).isEqualTo("subject-value");
    }

    @Test
    public void givenExpiredToken_ShouldThrowUnauthorizedException() {
        // GIVEN
        String expiredToken = "expired.jwt.token";
        when(credentialProperty.getHmacSecret()).thenReturn("secret-key");
        when(credentialProperty.getProvider()).thenReturn("test-provider");
        when(credentialProperty.convertToIntExpires()).thenReturn(1);

        // WHEN & THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(expiredToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Token is expired time");
    }

    @Test
    public void givenInvalidToken_ShouldThrowUnauthorizedException() {
        // GIVEN
        String invalidToken = "invalid.jwt.token";
        when(credentialProperty.getHmacSecret()).thenReturn("secret-key");
        when(credentialProperty.getProvider()).thenReturn("test-provider");
        when(credentialProperty.convertToIntExpires()).thenReturn(1);

        // WHEN & THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(invalidToken))
                .isInstanceOf(UnAuthorized.class)
                .hasMessage("Invalid token");
    }

    @Test
    public void givenSignatureVerificationFailure_ShouldThrowInternalServerError() {
        // GIVEN
        String malformedToken = "malformed.token";
        when(credentialProperty.getHmacSecret()).thenReturn("secret-key");
        when(credentialProperty.getProvider()).thenReturn("test-provider");
        when(credentialProperty.convertToIntExpires()).thenReturn(1);

        // WHEN & THEN
        assertThatThrownBy(() -> authComponent.decodeJwt(malformedToken))
                .isInstanceOf(InternalServerError.class)
                .hasMessage("Unknown signature secret key");
    }

    @Test
    public void givenValidUserDetails_ShouldGenerateCredentialWithCorrectClaims() {
        // GIVEN
        String userId = "user-123";
        String email = "user@example.com";
        when(credentialProperty.getHmacSecret()).thenReturn("secret-key");
        when(credentialProperty.getProvider()).thenReturn("test-provider");
        when(credentialProperty.convertToIntExpires()).thenReturn(24);

        // WHEN
        Credential credential = authComponent.generateJwt(userId, email, false);

        // THEN
        assertThat(credential.getToken()).isNotNull();
        assertThat(credential.getTokenType()).isEqualTo("Bearer");
        assertThat(credential.isRefresh()).isFalse();
        assertThat(credential.getExp()).isNotNull();
        assertThat(credential.getExp().getTime()).isGreaterThan(0L);
    }
}
