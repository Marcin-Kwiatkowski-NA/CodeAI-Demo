package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Test
class NotFoundGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test
  }

  @Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    NotFound exception = new NotFound();
    assertNotNull(exception);
    assertEquals(RuntimeException.class, exception.getClass());
  }

  @Test
  void constructor_withMsg() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A RuntimeException is created with the provided message.
    NotFound exception = new NotFound("Resource not found");
    assertNotNull(exception);
    assertEquals("Resource not found", exception.getMessage());
    assertEquals(RuntimeException.class, exception.getClass());
  }

  @Test
  void constructor_withCause() {
    // GIVEN: A cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the cause.
    NotFound exception = new NotFound(new NullPointerException());
    assertNotNull(exception);
    assertEquals(NullPointerException.class, exception.getCause().getClass());
  }

  @Test
  void constructor_withMsgAndCause() {
    // GIVEN: A message and a cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the message and cause.
    NotFound exception = new NotFound("Resource not found", new IllegalArgumentException());
    assertNotNull(exception);
    assertEquals("Resource not found", exception.getMessage());
    assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
  }
}
