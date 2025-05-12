package com.bestpractice.api.common.exception;

```

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.ExtensionPurpose;

class NotFound extends RuntimeException {

  public NotFound(String msg) {
    super(msg);
  }

  public NotFound(Throwable cause) {
    super(cause);
  }

  public NotFound(String msg, Throwable cause) {
    super(msg, cause);
  }

}

@ExtendWith Mockito
class TestNotFound {

  @Test
  void testNotFound_valid_message() {
    // Arrange
    String msg = "This is a valid message.";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }

  @Test
  void testNotFound_empty_message() {
    // Arrange
    String msg = "";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }

  @Test
  void testNotFound_message_with_error() {
    // Arrange
    String msg = "This is a message with an error.";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }

  @Test
  void testNotFound_multiple_messages() {
    // Arrange
    String msg1 = "First message";
    String msg2 = "Second message";
    NotFoundError b = new NotFound(msg1 + " and " + msg2);
    assert b.new(msg1 + " and " + msg2);
  }

  @Test
  void testNotFound_unknown_cause() {
    // Arrange
    String msg = "This is a message with an unknown cause.";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }
}

class MockitoTest {
  @Test
  void testNotFound_valid_message() {
    // Arrange
    String msg = "This is a valid message.";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }

  @Test
  void testNotFound_empty_message() {
    // Arrange
    String msg = "";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }

  @Test
  void testNotFound_message_with_error() {
    // Arrange
    String msg = "This is a message with an error.";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }

  @Test
  void testNotFound_multiple_messages() {
    // Arrange
    String msg1 = "First message";
    String msg2 = "Second message";
    NotFoundError b = new NotFound(msg1 + " and " + msg2);
    assert b.new(msg1 + " and " + msg2);
  }

  @Test
  void testNotFound_unknown_cause() {
    // Arrange
    String msg = "This is a message with an unknown cause.";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }
}

class TestMockitoTest {
  @Test
  void testNotFound_valid_message() {
    // Arrange
    String msg = "This is a valid message.";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }

  @Test
  void testNotFound_empty_message() {
    // Arrange
    String msg = "";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }

  @Test
  void testNotFound_message_with_error() {
    // Arrange
    String msg = "This is a message with an error.";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }

  @Test
  void testNotFound_multiple_messages() {
    // Arrange
    String msg1 = "First message";
    String msg2 = "Second message";
    NotFoundError b = new NotFound(msg1 + " and " + msg2);
    assert b.new(msg1 + " and " + msg2);
  }

  @Test
  void testNotFound_unknown_cause() {
    // Arrange
    String msg = "This is a message with an unknown cause.";
    NotFoundError b = new NotFound(msg);
    assert b.new(msg);
  }
}
