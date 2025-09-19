package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtensions.class)
class BadRequestGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test.  No specific reset needed for this simple class.
  }

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A Throwable cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Error message", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Error message", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}

// Dummy extension class to satisfy the annotation requirement.
class MyExtensions {}
