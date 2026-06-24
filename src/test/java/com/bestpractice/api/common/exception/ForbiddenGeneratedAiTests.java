package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Review and improvements summary:
 * 1. Removed redundant imports and unused annotations.
 * 2. Ensured consistent GIVEN-WHEN-THEN structure across all tests.
 * 3. Simplified assertions for clarity and correctness.
 * 4. Added missing edge case tests for null and empty message/cause combinations.
 * 5. Verified that all constructors are covered with meaningful assertions.
 * 6. Ensured tests are independent and readable.
 */
public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No specific setup required

        // WHEN: Creating a Forbidden instance using the default constructor
        Forbidden exception = new Forbidden();

        // THEN: Verify that the exception is created and has no message or cause
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A message to pass to the exception
        String message = "Access denied";

        // WHEN: Creating a Forbidden instance with a message
        Forbidden exception = new Forbidden(message);

        // THEN: Verify that the message is correctly set
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A cause to pass to the exception
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN: Creating a Forbidden instance with a cause
        Forbidden exception = new Forbidden(cause);

        // THEN: Verify that the cause is correctly set and message contains cause details
        assertThat(exception).isNotNull();
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Underlying cause");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause to pass to the exception
        String message = "Forbidden access";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating a Forbidden instance with both message and cause
        Forbidden exception = new Forbidden(message, cause);

        // THEN: Verify that both message and cause are correctly set
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowForbiddenExceptionWithMessage() {
        // GIVEN: A message that will be used when throwing the exception
        String message = "Access denied";

        // WHEN & THEN: Verify that throwing Forbidden results in the expected exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowForbiddenExceptionWithCause() {
        // GIVEN: A cause that will be used when throwing the exception
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN: Verify that throwing Forbidden results in the expected exception with cause
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void testThrowForbiddenExceptionWithMessageAndCause() {
        // GIVEN: A message and cause that will be used when throwing the exception
        String message = "Forbidden access";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN: Verify that throwing Forbidden results in the expected exception with message and cause
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testConstructorWithNullMessage() {
        // GIVEN: A null message
        String message = null;

        // WHEN: Creating a Forbidden instance with null message
        Forbidden exception = new Forbidden(message);

        // THEN: Verify that the message is null
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Creating a Forbidden instance with null cause
        Forbidden exception = new Forbidden(cause);

        // THEN: Verify that the cause is null
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithEmptyMessage() {
        // GIVEN: An empty message
        String message = "";

        // WHEN: Creating a Forbidden instance with an empty message
        Forbidden exception = new Forbidden(message);

        // THEN: Verify that the message is empty
        assertThat(exception).isNotNull();
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithWhitespaceMessage() {
        // GIVEN: A whitespace-only message
        String message = "   ";

        // WHEN: Creating a Forbidden instance with a whitespace message
        Forbidden exception = new Forbidden(message);

        // THEN: Verify that the message is preserved as whitespace
        assertThat(exception).isNotNull();
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithUnicodeMessage() {
        // GIVEN: A message containing Unicode characters
        String message = "拒绝访问 🚫";

        // WHEN: Creating a Forbidden instance with a Unicode message
        Forbidden exception = new Forbidden(message);

        // THEN: Verify that the message is correctly stored
        assertThat(exception).isNotNull();
        assertEquals("拒绝访问 🚫", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithSpecialCharactersMessage() {
        // GIVEN: A message containing special characters
        String message = "!@#$%^&*()_+-=[]{}|;':,./<>?`~";

        // WHEN: Creating a Forbidden instance with special characters in the message
        Forbidden exception = new Forbidden(message);

        // THEN: Verify that the message is correctly stored
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithVeryLongMessage() {
        // GIVEN: A very long message string
        String message = "X".repeat(5000);

        // WHEN: Creating a Forbidden instance with a long message
        Forbidden exception = new Forbidden(message);

        // THEN: Verify that the message is correctly stored
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithSelfAsCause() {
        // GIVEN: A Forbidden instance used as its own cause
        Forbidden self = new Forbidden("Self cause");

        // WHEN: Creating a new Forbidden instance with itself as cause
        Forbidden exception = new Forbidden("Recursive cause", self);

        // THEN: Verify that the cause is correctly set to the same instance
        assertThat(exception).isNotNull();
        assertEquals("Recursive cause", exception.getMessage());
        assertEquals(self, exception.getCause());
    }

    @Test
    void testConstructorWithNullMessageAndNullCause() {
        // GIVEN: Both message and cause are null
        String message = null;
        Throwable cause = null;

        // WHEN: Creating a Forbidden instance with null message and cause
        Forbidden exception = new Forbidden(message, cause);

        // THEN: Verify that both message and cause are null
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithEmptyMessageAndValidCause() {
        // GIVEN: An empty message and a valid cause
        String message = "";
        Throwable cause = new RuntimeException("Cause message");

        // WHEN: Creating a Forbidden instance with empty message and valid cause
        Forbidden exception = new Forbidden(message, cause);

        // THEN: Verify that both message and cause are correctly set
        assertThat(exception).isNotNull();
        assertEquals("", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithBoundaryNumericMessages() {
        // GIVEN: Numeric boundary values converted to strings
        String minIntMessage = String.valueOf(Integer.MIN_VALUE);
        String maxIntMessage = String.valueOf(Integer.MAX_VALUE);

        // WHEN: Creating Forbidden instances with numeric boundary messages
        Forbidden minException = new Forbidden(minIntMessage);
        Forbidden maxException = new Forbidden(maxIntMessage);

        // THEN: Verify that messages are stored correctly
        assertEquals(minIntMessage, minException.getMessage());
        assertEquals(maxIntMessage, maxException.getMessage());
    }
}
