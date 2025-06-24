package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ForbiddenGeneratedAiTests {

  @Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    RuntimeException exception = new Forbidden();
    Assertions.assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_withMsg() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    RuntimeException exception = new Forbidden("Test Message");
    Assertions.assertEquals("Test Message", exception.getMessage());
  }

  @Test
  void constructor_withThrowable() {
    // GIVEN: A Throwable object is passed to the constructor.
    // WHEN: The constructor is called with the Throwable object.
    // THEN: A RuntimeException is created with the Throwable object.
    Throwable cause = new RuntimeException("Cause");
    RuntimeException exception = new Forbidden(cause);
    Assertions.assertEquals(cause, exception.getCause());
  }

  @Test
  void constructor_withMsgAndThrowable() {
    // GIVEN: A message and a Throwable object are provided to the constructor.
    // WHEN: The constructor is called with the message and Throwable object.
    // THEN: A RuntimeException is created with the message and Throwable object.
    Throwable cause = new RuntimeException("Cause");
    Throwable expectedCause = new RuntimeException("Cause");
    RuntimeException exception = new Forbidden("Test Message", expectedCause);
    Assertions.assertEquals("Test Message", exception.getMessage());
    Assertions.assertEquals(expectedCause, exception.getCause());
  }
}
