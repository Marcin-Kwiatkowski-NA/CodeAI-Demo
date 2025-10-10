package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class BadRequestGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test
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
  void constructor_withThrowable() {
    // GIVEN: A throwable object is provided to the constructor.
    // WHEN: The constructor is called with a throwable object.
    // THEN: A RuntimeException is created with the provided throwable object.
    Throwable cause = new RuntimeException("Something went wrong");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertSame(cause, exception.getCause());
  }

  @Test
  void constructor_withMsgAndThrowable() {
    // GIVEN: A message and a throwable object are provided to the constructor.
    // WHEN: The constructor is called with a message and a throwable object.
    // THEN: A RuntimeException is created with the provided message and throwable object.
    Throwable cause = new RuntimeException("Detailed error");
    BadRequest exception = new BadRequest("Bad data", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("Bad data", exception.getMessage());
    assertSame(cause, exception.getCause());
  }
}

// Dummy extension class to satisfy the annotation requirement
class MyExtension {}
