package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InternalServerErrorGeneratedAiTests {

    @Test
    void constructor_no_args() {
        InternalServerError exception = new InternalServerError();
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_with_message() {
        InternalServerError exception = new InternalServerError("Something went wrong!");
        assertNotNull(exception);
        assertEquals("Something went wrong!", exception.getMessage());
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_with_cause() {
        InternalServerError exception = new InternalServerError(new NullPointerException());
        assertNotNull(exception);
        assertEquals(NullPointerException.class, exception.getCause().getClass());
    }

    @Test
    void constructor_with_message_and_cause() {
        InternalServerError exception = new InternalServerError("Error occurred", new IllegalArgumentException());
        assertNotNull(exception);
        assertEquals("Error occurred", exception.getMessage());
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
    }
}
