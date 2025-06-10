package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BadRequestGeneratedAiTests {

    private BadRequest badRequest;

    @BeforeEach
    public void setUp() {
        badRequest = null; // Reset before each test
    }

    @Test
    public void testBadRequestNoArgsConstructor() {
        // GIVEN
        // WHEN
        badRequest = new BadRequest();
        // THEN
        assertNotNull(badRequest);
    }

    @Test
    public void testBadRequestStringMsgConstructor() {
        // GIVEN
        String msg = "Error message";
        // WHEN
        badRequest = new BadRequest(msg);
        // THEN
        assertEquals(msg, badRequest.getMessage());
    }

    @Test
    public void testBadRequestThrowableCauseConstructor() {
        // GIVEN
        Throwable cause = new Exception("Cause");
        // WHEN
        badRequest = new BadRequest(cause);
        // THEN
        assertSame(cause, badRequest.getCause());
    }

    @Test
    public void testBadRequestStringMsgThrowableCauseConstructor() {
        // GIVEN
        String msg = "Error message";
        Throwable cause = new Exception("Cause");
        // WHEN
        badRequest = new BadRequest(msg, cause);
        // THEN
        assertEquals(msg, badRequest.getMessage());
        assertSame(cause, badRequest.getCause());
    }
}
