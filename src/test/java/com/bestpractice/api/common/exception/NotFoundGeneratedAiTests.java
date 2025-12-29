package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotFoundGeneratedAiTests {

    @Test
    public void testNotFoundDefaultConstructor() {
        // GIVEN: We create a new instance of NotFound using the default constructor
        NotFound notFound = new NotFound();

        // WHEN: We check if the instance is not null
        // THEN: The instance should not be null
        assertNotNull(notFound);
    }

    @Test
    public void testNotFoundWithMessage() {
        // GIVEN: We create a new instance of NotFound with a custom message
        String message = "Not Found";
        NotFound notFound = new NotFound(message);

        // WHEN: We check if the instance is not null and the message is correct
        // THEN: The instance should not be null and the message should match
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
    }

    @Test
    public void testNotFoundWithCause() {
        // GIVEN: We create a new instance of NotFound with a custom cause
        Throwable cause = new Throwable();
        NotFound notFound = new NotFound(cause);

        // WHEN: We check if the instance is not null and the cause is correct
        // THEN: The instance should not be null and the cause should match
        assertNotNull(notFound);
        assertEquals(cause, notFound.getCause());
    }

    @Test
    public void testNotFoundWithMessageAndCause() {
        // GIVEN: We create a new instance of NotFound with a custom message and cause
        String message = "Not Found";
        Throwable cause = new Throwable();
        NotFound notFound = new NotFound(message, cause);

        // WHEN: We check if the instance is not null and the message and cause are correct
        // THEN: The instance should not be null and the message and cause should match
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }
}
