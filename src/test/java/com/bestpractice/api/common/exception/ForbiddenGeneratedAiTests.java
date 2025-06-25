package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ForbiddenGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state if needed before each test.  In this case, no reset is required.
  }

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    Forbidden ex = new Forbidden();
    Assertions.assertInstanceOf(RuntimeException.class, ex);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    String message = "Test Message";
    Forbidden exception = new Forbidden(message);
    Assertions.assertInstanceOf(RuntimeException.class, exception);
    Assertions.assertEquals(message, exception.getMessage());
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A Throwable object is provided as the cause.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new RuntimeException("Cause Exception");
    Forbidden exception = new Forbidden(cause);
    Assertions.assertInstanceOf(RuntimeException.class, exception);
    Assertions.assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a Throwable object are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    String message = "Message Cause";
    Throwable cause = new RuntimeException("Cause");
    Forbidden exception = new Forbidden(message, cause);
    Assertions.assertInstanceOf(RuntimeException.class, exception);
    Assertions.assertEquals(message, exception.getMessage());
    Assertions.assertSame(cause, exception.getCause());
  }
}
