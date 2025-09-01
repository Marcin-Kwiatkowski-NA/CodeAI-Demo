package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.JUnit4;
import static org.junit.jupiter.api.Assertions.*;

@JUnit4
public class BadRequestGeneratedAiTests {

    private BadRequest badRequest;

    @org.junit.runners.model.BeforeAll
    void setUp() {
        badRequest = new BadRequest();
    }

    @org.junit.runners.model.Test
    void constructor_no_args() {
        // GIVEN: A new instance of BadRequest is created without any arguments.
        // WHEN: The constructor is called.
        // THEN: The constructor should call the super constructor with no arguments.
        badRequest = new BadRequest();
    }

    @org.junit.runners.model.Test
    void constructor_with_message() {
        // GIVEN: A new instance of BadRequest is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The constructor should call the super constructor with the message.
        badRequest = new BadRequest("Invalid request");
    }

    @org.junit.runners.model.Test
    void constructor_with_cause() {
        // GIVEN: A new instance of BadRequest is created with a Throwable cause.
        // WHEN: The constructor is called with a Throwable cause.
        // THEN: The constructor should call the super constructor with the cause.
        badRequest = new BadRequest(new NullPointerException());
    }

    @org.junit.runners.model.Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new instance of BadRequest is created with a message and a Throwable cause.
        // WHEN: The constructor is called with a message and a Throwable cause.
        // THEN: The constructor should call the super constructor with the message and the cause.
        badRequest = new BadRequest("Error occurred", new IllegalArgumentException());
    }
}
