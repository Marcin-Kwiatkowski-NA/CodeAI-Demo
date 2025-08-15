package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
class NotFoundGeneratedAiTests {

  @BeforeEach
  void setUp() {
    // Reset state before each test
  }

  @Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor
    // WHEN: The constructor is called
    // THEN: A RuntimeException is created with no message
    NotFound exception = new NotFound();
    assertNotNull(exception);
    assertEquals(RuntimeException.class, exception.getClass());
  }

  @Test
  void constructor_withMsg() {
    // GIVEN: A message is passed to the constructor
    // WHEN: The constructor is called
    // THEN: A RuntimeException is created with the provided message
    NotFound exception = new NotFound("Resource not found");
    assertNotNull(exception);
    assertEquals(RuntimeException.class, exception.getClass());
    assertEquals("Resource not found", exception.getMessage());
  }

  @Test
  void constructor_withCause() {
    // GIVEN: A Throwable cause is passed to the constructor
    // WHEN: The constructor is called
    // THEN: A RuntimeException is created with the provided cause
    NotFound exception = new NotFound(new NullPointerException());
    assertNotNull(exception);
    assertEquals(RuntimeException.class, exception.getClass());
    assertSame(NullPointerException.class, exception.getCause());
  }

  @Test
  void constructor_withMsgAndCause() {
    // GIVEN: A message and a Throwable cause are passed to the constructor
    // WHEN: The constructor is called
    // THEN: A RuntimeException is created with the provided message and cause
    NotFound exception = new NotFound("Resource not found", new IllegalArgumentException());
    assertNotNull(exception);
    assertEquals(RuntimeException.class, exception.getClass());
    assertEquals("Resource not found", exception.getMessage());
    assertSame(IllegalArgumentException.class, exception.getCause());
  }
}
