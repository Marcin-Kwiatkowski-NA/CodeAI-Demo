package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import java.util.Objects;

class NotFoundTest {

    @Test
    void testNullMessage() {
        assertThrows(NoSuchCaught, () -> {
            Objects.requireNonNull("NotFound", "Valid Message");
        });
    }

    @Test
    void testMessageWithThrowable() {
        assertThrows(NoSuchCaught, () -> {
            Objects.requireNonNull("NotFound", "Throwable Message");
        });
    }

    @Test
    void testNotFoundWithStringMessage() {
        assertThrows(NoSuchCaught, () -> {
            Objects.requireNonNull("NotFound", "String Message");
        });
    }

    @Test
    void testNotFoundWithThrowableAndStringMessage() {
        assertThrows(NoSuchCaught, () -> {
            Objects.requireNonNull("NotFound", "Throwable and String Message");
        });
    }

    @Test
    void testNotFoundWithThrowableAndStringMessageAndStringMessage() {
        assertThrows(NoSuchCaught, () -> {
            Objects.requireNonNull("NotFound", "Throwable and String Message and String Message");
        });
    }

    @Test
    void testNotFoundWithStringMessageAndThrowable() {
        assertThrows(NoSuchCaught, () -> {
            Objects.requireNonNull("NotFound", "String Message and Throwable");
        });
    }
}
