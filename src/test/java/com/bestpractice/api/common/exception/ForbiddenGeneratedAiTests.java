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

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreateForbiddenWithNoArgs() {
        // GIVEN

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateForbiddenWithMessage() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateForbiddenWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void shouldCreateForbiddenWithMessageAndCause() {
        // GIVEN
        String message = "Forbidden action";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowForbiddenWhenExplicitlyThrown() {
        // GIVEN
        String message = "Explicit forbidden throw";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowForbiddenWithNullMessage() {
        // GIVEN
        String message = null;

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(null, thrown.getMessage());
    }

    @Test
    void shouldThrowForbiddenWithNullCause() {
        // GIVEN
        Throwable cause = null;

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden("Null cause test", cause);
        });
        assertEquals("Null cause test", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void shouldThrowForbiddenWithNullMessageAndCause() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });
        assertEquals(null, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void shouldHandleEmptyMessageGracefully() {
        // GIVEN
        String message = "";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleWhitespaceOnlyMessage() {
        // GIVEN
        String message = "   ";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleLongMessageBoundary() {
        // GIVEN
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("A");
        }
        String longMessage = sb.toString();

        // WHEN
        Forbidden exception = new Forbidden(longMessage);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals(longMessage, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleSingleCharacterMessage() {
        // GIVEN
        String message = "X";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals("X", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleSelfReferencingCause() {
        // GIVEN
        Forbidden selfCause = new Forbidden("Self cause");

        // WHEN
        Forbidden exception = new Forbidden("Self reference", selfCause);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals("Self reference", exception.getMessage());
        assertEquals(selfCause, exception.getCause());
    }

    @Test
    void shouldHandleDeepNestedCauseChain() {
        // GIVEN
        Throwable deepCause = new RuntimeException("Deep cause");
        Throwable midCause = new IllegalStateException("Mid cause", deepCause);

        // WHEN
        Forbidden exception = new Forbidden("Top level", midCause);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals("Top level", exception.getMessage());
        assertEquals(midCause, exception.getCause());
        assertThat(exception.getCause().getCause()).isEqualTo(deepCause);
    }

    @Test
    void shouldHandleEmptyMessageAndNonNullCause() {
        // GIVEN
        String message = "";
        Throwable cause = new RuntimeException("Cause exists");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals("", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldHandleWhitespaceMessageAndNullCause() {
        // GIVEN
        String message = " ";
        Throwable cause = null;

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals(" ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldPreserveCauseMessageWhenOnlyCauseProvided() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertThat(exception.getMessage()).contains("Underlying issue");
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldNotModifyMessageOrCauseAfterCreation() {
        // GIVEN
        String message = "Immutable test";
        Throwable cause = new RuntimeException("Immutable cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo("Immutable test");
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
