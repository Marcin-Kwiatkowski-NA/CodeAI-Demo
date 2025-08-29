package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void testGetSetStatus() {
        // GIVEN: A new ErrorResponse object
        // WHEN: The status is set to 500
        errorResponse.setStatus(500);
        // THEN: The status is updated to 500
        assertEquals(500, errorResponse.getStatus());
    }

    @Test
    void testGetSetError() {
        // GIVEN: A new ErrorResponse object
        // WHEN: The error is set to "Internal Server Error"
        errorResponse.setError("Internal Server Error");
        // THEN: The error is updated to "Internal Server Error"
        assertEquals("Internal Server Error", errorResponse.getError());
    }

    @Test
    void testGet setMessage() {
        // GIVEN: A new ErrorResponse object
        // WHEN: The message is set to "Something went wrong"
        errorResponse.setMessage("Something went wrong");
        // THEN: The message is updated to "Something went wrong"
        assertEquals("Something went wrong", errorResponse.getMessage());
    }
}
