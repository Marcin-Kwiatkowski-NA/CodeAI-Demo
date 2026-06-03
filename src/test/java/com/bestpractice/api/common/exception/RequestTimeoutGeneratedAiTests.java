package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class RequestTimeoutGeneratedAiTests {

    private RequestTimeout requestTimeout;

    @BeforeEach
    void setUp() {
        requestTimeout = null;
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions

        // WHEN
        requestTimeout = new RequestTimeout();

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(null, requestTimeout.getMessage());
        assertEquals(null, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN
        String message = "Request timed out";

        // WHEN
        requestTimeout = new RequestTimeout(message);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(message, requestTimeout.getMessage());
        assertEquals(null, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN
        requestTimeout = new RequestTimeout(cause);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(cause, requestTimeout.getCause());
        assertThat(requestTimeout.getMessage()).contains("Underlying cause");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN
        String message = "Timeout occurred";
        Throwable cause = new RuntimeException("Network issue");

        // WHEN
        requestTimeout = new RequestTimeout(message, cause);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(message, requestTimeout.getMessage());
        assertEquals(cause, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithNullMessage() {
        // GIVEN
        String message = null;

        // WHEN
        requestTimeout = new RequestTimeout(message);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(null, requestTimeout.getMessage());
        assertEquals(null, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithNullCause() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        requestTimeout = new RequestTimeout(cause);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(null, requestTimeout.getCause());
        assertEquals(null, requestTimeout.getMessage());
    }

    @Test
    void testConstructorWithNullMessageAndCause() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        requestTimeout = new RequestTimeout(message, cause);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(null, requestTimeout.getMessage());
        assertEquals(null, requestTimeout.getCause());
    }

    @Test
    void testThrowingRequestTimeoutException() {
        // GIVEN
        String message = "Simulated timeout";

        // WHEN & THEN
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout(message);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingRequestTimeoutExceptionWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        RequestTimeout thrown = assertThrows(RequestTimeout.class, () -> {
            throw new RequestTimeout("Timeout with cause", cause);
        });
        assertEquals("Timeout with cause", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testConstructorWithEmptyMessage() {
        // GIVEN
        String message = "";

        // WHEN
        requestTimeout = new RequestTimeout(message);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals("", requestTimeout.getMessage());
        assertEquals(null, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithWhitespaceMessage() {
        // GIVEN
        String message = "   ";

        // WHEN
        requestTimeout = new RequestTimeout(message);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals("   ", requestTimeout.getMessage());
        assertEquals(null, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithLongMessage() {
        // GIVEN
        String message = "A".repeat(10000);

        // WHEN
        requestTimeout = new RequestTimeout(message);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(message, requestTimeout.getMessage());
        assertEquals(null, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithSingleCharacterMessage() {
        // GIVEN
        String message = "X";

        // WHEN
        requestTimeout = new RequestTimeout(message);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals("X", requestTimeout.getMessage());
        assertEquals(null, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithSpecialCharactersMessage() {
        // GIVEN
        String message = "!@#$%^&*()_+{}|:\"<>?";

        // WHEN
        requestTimeout = new RequestTimeout(message);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(message, requestTimeout.getMessage());
        assertEquals(null, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithEmptyMessageAndCause() {
        // GIVEN
        String message = "";
        Throwable cause = new RuntimeException("Empty message cause");

        // WHEN
        requestTimeout = new RequestTimeout(message, cause);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals("", requestTimeout.getMessage());
        assertEquals(cause, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithWhitespaceMessageAndCause() {
        // GIVEN
        String message = "   ";
        Throwable cause = new RuntimeException("Whitespace cause");

        // WHEN
        requestTimeout = new RequestTimeout(message, cause);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals("   ", requestTimeout.getMessage());
        assertEquals(cause, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithLongMessageAndCause() {
        // GIVEN
        String message = "B".repeat(5000);
        Throwable cause = new RuntimeException("Long message cause");

        // WHEN
        requestTimeout = new RequestTimeout(message, cause);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals(message, requestTimeout.getMessage());
        assertEquals(cause, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithSingleCharacterMessageAndCause() {
        // GIVEN
        String message = "Y";
        Throwable cause = new RuntimeException("Single char cause");

        // WHEN
        requestTimeout = new RequestTimeout(message, cause);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals("Y", requestTimeout.getMessage());
        assertEquals(cause, requestTimeout.getCause());
    }

    @Test
    void testConstructorWithSpecialCharactersMessageAndCause() {
        // GIVEN
        String message = "~!@#$%^&*()_+";
        Throwable cause = new RuntimeException("Special chars cause");

        // WHEN
        requestTimeout = new RequestTimeout(message, cause);

        // THEN
        assertThat(requestTimeout).isNotNull();
        assertEquals("~!@#$%^&*()_+", requestTimeout.getMessage());
        assertEquals(cause, requestTimeout.getCause());
    }

    @Test
    void testToStringContainsMessageWhenPresent() {
        // GIVEN
        String message = "Timeout occurred";

        // WHEN
        requestTimeout = new RequestTimeout(message);

        // THEN
        assertThat(requestTimeout.toString()).contains("Timeout occurred");
    }

    @Test
    void testToStringContainsClassName() {
        // GIVEN
        requestTimeout = new RequestTimeout("Timeout test");

        // WHEN
        String result = requestTimeout.toString();

        // THEN
        assertThat(result).contains("RequestTimeout");
    }
}
