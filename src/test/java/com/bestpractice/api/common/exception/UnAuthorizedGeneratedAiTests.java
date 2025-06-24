package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;

class UnAuthorizedGeneratedAiTests {

    @ExtendWith(MyExtension.class)
    public static class MyExtension {
    }

    @Test
    public void constructor_no_args() {
        UnAuthorized exception = new UnAuthorized();
        assert exception instanceof RuntimeException;
    }

    @Test
    public void constructor_with_message() {
        UnAuthorized exception = new UnAuthorized("Unauthorized access");
        assert exception instanceof RuntimeException;
        assert Objects.equals("Unauthorized access", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        UnAuthorized exception = new UnAuthorized(new NullPointerException());
        assert exception instanceof RuntimeException;
        assert exception.getCause() == null;
    }

    @Test
    public void constructor_with_message_and_cause() {
        UnAuthorized exception = new UnAuthorized("Error", new IllegalArgumentException("Invalid input"));
        assert exception instanceof RuntimeException;
        assert Objects.equals("Error", exception.getMessage());
        assert exception.getCause() == null;
    }

    @BeforeEach
    public void setUp() {
    }
}
