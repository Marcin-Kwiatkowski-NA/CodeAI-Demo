package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

public class ServiceUnavailableGeneratedAiTests {

    @Test
    public void constructor_no_args() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assert exception instanceof RuntimeException;
    }

    @Test
    public void constructor_with_message() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        assert exception instanceof RuntimeException;
        assert exception.getMessage() == "Service is temporarily unavailable";
    }

    @Test
    public void constructor_with_cause() {
        ServiceUnavailable exception = new ServiceUnavailable(new NullPointerException());
        assert exception instanceof RuntimeException;
        assert exception.getCause() == nullPointerException;
    }

    @Test
    public void constructor_with_message_and_cause() {
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", new IllegalArgumentException());
        assert exception instanceof RuntimeException;
        assert exception.getMessage() == "Service is temporarily unavailable";
        assert exception.getCause() == IllegalArgumentException;
    }
}
