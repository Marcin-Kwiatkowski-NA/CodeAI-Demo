package com.bestpractice.api.common.exception;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(com.bestpractice.api.common.exception.InternalServerErrorGeneratedAiTests.class)
class InternalServerErrorGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test.  No specific reset needed for this simple class.
  }

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A RuntimeException is created with no message.
    InternalServerError exception = new InternalServerError();
    assertNotNull(exception);
    assertEquals(RuntimeException.class, exception.getClass());
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the provided message.
    // THEN: A RuntimeException is created with the provided message.
    InternalServerError exception = new InternalServerError("Something went wrong!");
    assertNotNull(exception);
    assertEquals("Something went wrong!", exception.getMessage());
    assertEquals(RuntimeException.class, exception.getClass());
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A Throwable cause is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A RuntimeException is created with the provided cause.
    InternalServerError exception = new InternalServerError(new NullPointerException());
    assertNotNull(exception);
    assertEquals(NullPointerException.class, exception.getCause().getClass());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a Throwable cause are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A RuntimeException is created with the provided message and cause.
    InternalServerError exception = new InternalServerError("Error occurred", new IllegalArgumentException());
    assertNotNull(exception);
    assertEquals("Error occurred", exception.getMessage());
    assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
  }
}