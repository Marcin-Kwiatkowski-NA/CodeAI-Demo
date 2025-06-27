package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ServiceUnavailableGeneratedAiTests {

    @Test
    public void constructor_no_args() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A ServiceUnavailable exception is thrown with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assert exception instanceof ServiceUnavailable;
    }

    @Test
    public void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A ServiceUnavailable exception is thrown with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        assert exception instanceof ServiceUnavailable;
        assertEquals("Service is temporarily unavailable", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        // GIVEN: A cause is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A ServiceUnavailable exception is thrown with the cause.
        ServiceUnavailable exception = new ServiceUnavailable(new IllegalArgumentException("Invalid input data"));
        assert exception instanceof ServiceUnavailable;
        assertEquals("Invalid input data", exception.getMessage());
    }

    @Test
    public void constructor_with_message_and_cause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A ServiceUnavailable exception is thrown with the message and cause.
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", new NullPointerException("Request object is null"));
        assert exception instanceof ServiceUnavailable;
        assertEquals("Request object is null", exception.getMessage());
    }
}
