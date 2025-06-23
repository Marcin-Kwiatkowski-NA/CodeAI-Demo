package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testGetStatusReturnsCorrectValue() {
        ErrorResponse error = new ErrorResponse();
        assertEquals(1, error.getStatus());
    }

    @Test
    void testGetErrorReturnsCorrectError() {
        ErrorResponse error = new ErrorResponse();
        assertEquals("Failed to initialize the service.", error.getError());
    }

    @Test
    void testGetMessageReturnsCorrectValue() {
        ErrorResponse error = new ErrorResponse();
        assertEquals(2, error.getError());
    }

    @Test
    void testSetStatusReturnsCorrectValue() {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(2);
        assertEquals(2, error.getStatus());
    }

    @Test
    void testSetErrorReturnsCorrectError() {
        ErrorResponse error = new ErrorResponse();
        error.setError("Something went wrong.");
        assertEquals("Invalid input.", error.getError());
    }

    @Test
    void testSetMessageReturnsCorrectValue() {
        ErrorResponse error = new ErrorResponse();
        assertEquals("Test message.", error.getMessage());
    }

    @Test
    void testSetErrorMessageReturnsCorrectMessage() {
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Test message.")
        assertEquals("Test message.", error.getMessage());
    }

    @Test
    void testSetStatusReturnsCorrectValue() {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(3);
        assertEquals(3, error.getStatus());
    }

    @Test
    void testSetErrorReturnsCorrectError() {
        ErrorResponse error = new ErrorResponse();
        error.setError("Invalid input.");
        assertEquals("Invalid input.", error.getError());
    }

    @Test
    void testSetMessageReturnsCorrectMessage() {
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Test message.")
        assertEquals("Test message.", error.getMessage());
    }
}
