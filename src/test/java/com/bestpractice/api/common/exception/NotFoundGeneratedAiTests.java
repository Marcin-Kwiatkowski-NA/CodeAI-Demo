package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotFoundGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset any state needed before each test.  Not applicable in this simple case.
  }

  @Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    NotFound exception = new NotFound();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_withMsg() {
    // GIVEN: A message is passed to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    NotFound exception = new NotFound("Resource not found");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_withCause() {
    // GIVEN: A Throwable cause is passed to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the cause.
    NotFound exception = new NotFound(new NullPointerException());
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(NullPointerException.class, exception.getCause());
  }

  @Test
  void constructor_withMsgAndCause() {
    // GIVEN: A message and a Throwable cause are passed to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the message and cause.
    NotFound exception = new NotFound("Error occurred", new IllegalArgumentException());
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Error occurred", exception.getMessage());
    assertSame(IllegalArgumentException.class, exception.getCause());
  }
}
