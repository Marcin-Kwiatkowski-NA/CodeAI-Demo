package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @Test
    void testGetSetStatus() {
        // GIVEN: A new ErrorResponse object
        // WHEN: The status is set to 500
        errorResponse.setStatus(500);
        // THEN: The status is 500
        assertEquals(500, errorResponse.getStatus());
    }

    @Test
    void testGetSetError() {
        // GIVEN: A new ErrorResponse object
        // WHEN: The error is set to "Internal Server Error"
        errorResponse.setError("Internal Server Error");
        // THEN: The error is "Internal Server Error"
        assertEquals("Internal Server Error", errorResponse.getError());
    }

    @Test
    void testGet setMessage() {
        // GIVEN: A new ErrorResponse object
        // WHEN: The message is set to "Something went wrong"
        errorResponse.setMessage("Something went wrong");
        // THEN: The message is "Something went wrong"
        assertEquals("Something went wrong", errorResponse.getMessage());
    }
}
