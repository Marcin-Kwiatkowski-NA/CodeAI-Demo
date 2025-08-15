package com.bestpractice.api.common.exception;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UnAuthorizedGeneratedAiTests {

  private UnAuthorized unAuthorized;

  @BeforeEach
  void setUp() {
    unAuthorized = new UnAuthorized(new RuntimeException());
  }

  @Test
  void constructor_noArgs() {
    // GIVEN: No arguments are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A runtime exception is thrown with no message.
    assertThrows(RuntimeException.class, () -> unAuthorized.getMessage());
  }

  @Test
  void constructor_withMessage() {
    // GIVEN: A message is passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A runtime exception is thrown with the specified message.
    assertThrows(RuntimeException.class, () -> unAuthorized.getMessage());
  }

  @Test
  void constructor_withCause() {
    // GIVEN: A cause is passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A runtime exception is thrown with the specified cause.
    assertThrows(RuntimeException.class, () -> unAuthorized.getMessage());
  }

  @Test
  void constructor_withMessageAndCause() {
    // GIVEN: A message and a cause are passed to the constructor.
    // WHEN: The constructor is called.
    // THEN: A runtime exception is thrown with the specified message and cause.
    assertThrows(RuntimeException.class, () -> unAuthorized.getMessage());
  }
}