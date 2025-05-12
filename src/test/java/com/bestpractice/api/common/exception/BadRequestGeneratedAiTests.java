package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.Extension;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.ExtendWith(MyExtension.class)
class BadRequestGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state if needed before each test.  In this case, no reset is required.
  }

  @Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called without arguments.
    // THEN: A RuntimeException is created with no message.
    BadRequest exception = new BadRequest();
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  void constructor_withMsg() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with a message.
    // THEN: A RuntimeException is created with the provided message.
    BadRequest exception = new BadRequest("Invalid request");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
  }

  @Test
  void constructor_withCause() {
    // GIVEN: A cause is provided to the constructor.
    // WHEN: The constructor is called with a cause.
    // THEN: A RuntimeException is created with the provided cause.
    Throwable cause = new RuntimeException("Underlying error");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_withMsgAndCause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with a message and a cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    Throwable cause = new RuntimeException("Underlying error");
    BadRequest exception = new BadRequest("Invalid request", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Invalid request", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}

class MyExtension implements Extension {}
