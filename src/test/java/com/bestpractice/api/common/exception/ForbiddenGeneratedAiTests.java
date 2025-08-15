package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

class ForbiddenGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test.  No specific reset needed for this simple class.
  }

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    Forbidden exception = new Forbidden();
    assertNotNull(exception);
    assertEquals(RuntimeException.class, exception.getClass());
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    Forbidden exception = new Forbidden("Access denied");
    assertNotNull(exception);
    assertEquals("Access denied", exception.getMessage());
    assertEquals(RuntimeException.class, exception.getClass());
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A cause (Throwable) is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    String message = "Something went wrong";
    Throwable cause = new Throwable("Detailed error message");
    Forbidden exception = new Forbidden(cause);
    assertNotNull(exception);
    assertEquals(cause, exception.getCause());
    assertEquals(RuntimeException.class, exception.getClass());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    String message = "Invalid credentials";
    Throwable cause = new Throwable("Authentication failed");
    Forbidden exception = new Forbidden(message, cause);
    assertNotNull(exception);
    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
    assertEquals(RuntimeException.class, exception.getClass());
  }
}
