package com.example;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthenticatorGeneratedAiTests {

    private Authenticator authenticator;

    @BeforeEach
    void setUp() {
        // GIVEN a new Authenticator instance with specific values
        authenticator = new Authenticator("Bearer", "token123", "refreshToken123", new Date());
    }

    @Test
    void testGetTokenType() {
        // GIVEN an Authenticator instance
        // WHEN retrieving the token type
        String tokenType = authenticator.getTokenType();
        // THEN it should match the value passed to the constructor
        assertThat(tokenType).isEqualTo("Bearer");
    }

    @Test
    void testGetToken() {
        // GIVEN an Authenticator instance
        // WHEN retrieving the token
        String token = authenticator.getToken();
        // THEN it should match the value passed to the constructor
        assertThat(token).isEqualTo("token123");
    }

    @Test
    void testGetRefreshToken() {
        // GIVEN an Authenticator instance
        // WHEN retrieving the refresh token
        String refreshToken = authenticator.getRefreshToken();
        // THEN it should match the value passed to the constructor
        assertThat(refreshToken).isEqualTo("refreshToken123");
    }

    @Test
    void testGetExpiresAtReturnsSameDateInstance() {
        // GIVEN an Authenticator instance with a specific Date
        Date expiresAt = authenticator.getExpiresAt();
        // WHEN retrieving the expiresAt date again
        Date expiresAtAgain = authenticator.getExpiresAt();
        // THEN the returned instance should be the same as the original
        assertThat(expiresAt).isSameAs(expiresAtAgain);
    }

    @Test
    void testExpiresAtCanBeNull() {
        // GIVEN a new Authenticator with null expiresAt
        Authenticator authenticatorWithNullExpires = new Authenticator("Bearer", "token", "refresh", null);
        // WHEN retrieving expiresAt
        Date expiresAt = authenticatorWithNullExpires.getExpiresAt();
        // THEN it should be null
        assertThat(expiresAt).isNull();
    }

    @Test
    void testConstructorThrowsNullPointerExceptionForNullTokenType() {
        // GIVEN null tokenType
        // WHEN creating an Authenticator with null tokenType
        // THEN a NullPointerException should be thrown
        assertThatThrownBy(() -> new Authenticator(null, "token", "refresh", new Date()))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testConstructorThrowsNullPointerExceptionForNullToken() {
        // GIVEN null token
        // WHEN creating an Authenticator with null token
        // THEN a NullPointerException should be thrown
        assertThatThrownBy(() -> new Authenticator("Bearer", null, "refresh", new Date()))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testConstructorThrowsNullPointerExceptionForNullRefreshToken() {
        // GIVEN null refreshToken
        // WHEN creating an Authenticator with null refreshToken
        // THEN a NullPointerException should be thrown
        assertThatThrownBy(() -> new Authenticator("Bearer", "token", null, new Date()))
                .isInstanceOf(NullPointerException.class);
    }
}
