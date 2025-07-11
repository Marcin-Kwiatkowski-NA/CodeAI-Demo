package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;

public class InternalServerErrorGeneratedAiTests {

  @Test
  public void constructor_no_args() {
    InternalServerError exception = new InternalServerError();
    Assertions.assertInstanceOf(RuntimeException.class, exception);
  }

  @Test
  public void constructor_with_message() {
    InternalServerError exception = new InternalServerError("An unexpected error occurred.");
    Assertions.assertInstanceOf(RuntimeException.class, exception);
    Assertions.assertEquals("An unexpected error occurred.", exception.getMessage());
  }

  @Test
  public void constructor_with_cause() {
    InternalServerError exception = new InternalServerError(new NullPointerException());
    Assertions.assertInstanceOf(RuntimeException.class, exception);
    Assertions.assertEquals(new NullPointerException(), exception.getCause());
  }

  @Test
  public void constructor_with_message_and_cause() {
    InternalServerError exception = new InternalServerError("An error occurred", new IllegalArgumentException());
    Assertions.assertInstanceOf(RuntimeException.class, exception);
    Assertions.assertEquals("An error occurred", exception.getMessage());
    Assertions.assertEquals(new IllegalArgumentException(), exception.getCause());
  }
}
