package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

class ConflictGeneratedAiTests {

  private Conflict conflict;

  @BeforeEach
  void setUp() {
    conflict = new Conflict();
  }

  @Test
  void constructor_no_args() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A Conflict object is created with no message.
    assertNotNull(conflict);
  }

  @Test
  void constructor_with_message() {
    // GIVEN: A message is provided to the constructor.
    // WHEN: The constructor is called with the message.
    // THEN: A Conflict object is created with the specified message.
    conflict = new Conflict("This is a conflict message.");
    assertEquals("This is a conflict message.", conflict.getMessage());
  }

  @Test
  void constructor_with_cause() {
    // GIVEN: A cause (Throwable) is provided to the constructor.
    // WHEN: The constructor is called with the cause.
    // THEN: A Conflict object is created with the specified message and cause.
    conflict = new Conflict(new NullPointerException());
    assertEquals("NullPointerException", conflict.getMessage());
    assertSame(NullPointerException.class, conflict.getCause());
  }

  @Test
  void constructor_with_message_and_cause() {
    // GIVEN: A message and a cause (Throwable) are provided to the constructor.
    // WHEN: The constructor is called with the message and cause.
    // THEN: A Conflict object is created with the specified message and cause.
    conflict = new Conflict("Something went wrong", new IllegalArgumentException());
    assertEquals("Something went wrong", conflict.getMessage());
    assertEquals("IllegalArgumentException", conflict.getCause().getMessage());
    assertSame(IllegalArgumentException.class, conflict.getCause());
  }
}
