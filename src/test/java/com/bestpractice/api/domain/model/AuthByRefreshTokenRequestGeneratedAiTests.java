package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest authByRefreshTokenRequest;

    @BeforeEach
    void setUp() {
        authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
    }

    @Test
    void shouldSetAndGetRefreshTokenSuccessfully() {
        // GIVEN - a valid refresh token
        String expectedToken = "sampleRefreshToken";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns the same value
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldHandleNullRefreshTokenGracefully() {
        // GIVEN - a null refresh token
        String expectedToken = null;

        // WHEN - setting the refresh token to null
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns null
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldAllowEmptyStringAsRefreshToken() {
        // GIVEN - an empty string
        String expectedToken = "";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns empty string
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldAllowWhitespaceOnlyRefreshToken() {
        // GIVEN - whitespace-only string
        String expectedToken = "   ";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns same whitespace string
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldAllowSingleCharacterRefreshToken() {
        // GIVEN - single character string
        String expectedToken = "a";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns same single character
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldAllowVeryLongRefreshToken() {
        // GIVEN - a very long string (boundary test)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("x");
        }
        String expectedToken = sb.toString();

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns same long string
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldReturnNullWhenRefreshTokenNotSet() {
        // GIVEN - a new instance without setting refresh token

        // WHEN - getting refresh token
        String token = authByRefreshTokenRequest.getRefreshToken();

        // THEN - verify it returns null
        assertEquals(null, token);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenAccessingLengthOfUnsetRefreshToken() {
        // GIVEN - refresh token not set (null)
        authByRefreshTokenRequest.setRefreshToken(null);

        // WHEN & THEN - accessing length should throw NullPointerException
        assertThrows(NullPointerException.class, () -> {
            String token = authByRefreshTokenRequest.getRefreshToken();
            int length = token.length();
        });
    }

    @Test
    void shouldPreserveLeadingAndTrailingWhitespaceInRefreshToken() {
        // GIVEN - a token with leading and trailing spaces
        String expectedToken = "  token  ";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter preserves whitespace
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldHandleUnicodeCharactersInRefreshToken() {
        // GIVEN - a token containing Unicode characters
        String expectedToken = "тест令牌✓";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns same Unicode string
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldHandleSpecialCharactersInRefreshToken() {
        // GIVEN - a token with special characters
        String expectedToken = "!@#$%^&*()_+-=[]{}|;':,.<>?/";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns same special character string
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldHandleMixedWhitespaceAndCharactersInRefreshToken() {
        // GIVEN - a token with mixed whitespace and characters
        String expectedToken = " token with spaces ";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns same mixed string
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldHandleNumericStringAsRefreshToken() {
        // GIVEN - a numeric string
        String expectedToken = "1234567890";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns same numeric string
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldHandleCombinationOfLettersNumbersAndSymbolsInRefreshToken() {
        // GIVEN - a complex token
        String expectedToken = "Abc123!@#XYZ";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify getter returns same complex string
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }
}
