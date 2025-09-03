package com.bestpractice.api.common.exception;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

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
    BadRequest exception = new BadRequest("This is a test message");
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("This is a test message", exception.getMessage());
  }

  @Test
  void constructor_withThrowable() {
    // GIVEN: A Throwable object is provided to the constructor.
    // WHEN: The constructor is called with a Throwable object.
    // THEN: A RuntimeException is created with the Throwable object's message.
    Throwable cause = new NullPointerException("Simulated cause");
    BadRequest exception = new BadRequest(cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals(cause.getMessage(), exception.getMessage());
  }

  @Test
  void constructor_withMsgAndThrowable() {
    // GIVEN: A message and a Throwable object are provided to the constructor.
    // WHEN: The constructor is called with a message and a Throwable object.
    // THEN: A RuntimeException is created with the provided message and the Throwable object's message.
    Throwable cause = new NullPointerException("Simulated cause");
    BadRequest exception = new BadRequest("This is a message", cause);
    assertNotNull(exception);
    assertInstanceOf(RuntimeException.class, exception);
    assertEquals("This is a message", exception.getMessage());
    assertEquals(cause.getMessage(), exception.getMessage());
  }
}