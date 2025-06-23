package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceUnavailableGeneratedAiTests {

  @Test
  void testServiceUnavailable_noMessage() {
    ServiceUnavailable s = new ServiceUnavailable();
    assertThrows(RuntimeException.class, () -> {
      s();
    });
  }

  @Test
  void testServiceUnavailable_simpleMessage() {
    ServiceUnavailable s = new ServiceUnavailable("This is a simple message.");
    assertThrows(RuntimeException.class, () -> {
      s();
    });
  }

  @Test
  void testServiceUnavailable_complexMessage() {
    ServiceUnavailable s = new ServiceUnavailable("An error occurred.  The service is unavailable.");
    assertThrows(RuntimeException.class, () -> {
      s();
    });
  }

  @Test
  void testServiceUnavailable_multipleMessages() {
    ServiceUnavailable s = new ServiceUnavailable("First message failed. Second message succeeded.");
    assertThrows(RuntimeException.class, () -> {
      s();
    });
  }

  @Test
  void testServiceUnavailable_emptyMessage() {
    ServiceUnavailable s = new ServiceUnavailable();
    assertThrows(RuntimeException.class, () -> {
      s();
    });
  }
}
