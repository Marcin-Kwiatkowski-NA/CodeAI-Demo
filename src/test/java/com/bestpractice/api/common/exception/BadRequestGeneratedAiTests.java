package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BadRequestGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset any state needed before each test.  Not applicable in this simple case, but included for completeness.
  }

  @Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_withMessage() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @Test
  void constructor_withThrowable() {
    // GIVEN: A Throwable object is provided to the constructor.
    // WHEN: The constructor is called with the Throwable object.
    // THEN: A RuntimeException is created with the Throwable object.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_withMessageAndThrowable() {
    // GIVEN: A message and a Throwable object are provided to the constructor.
    // WHEN: The constructor is called with the message and the Throwable object.
    // THEN: A RuntimeException is created with the message and the Throwable object.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Bad data", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Bad data", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}
