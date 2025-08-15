package com.bestpractice.api.common.exception;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class ServiceUnavailableGeneratedAiTests {

  private ServiceUnavailable exception;

  @BeforeEach
  void setUp() {
    exception = Mockito.newMock(ServiceUnavailable.class);
  }

  @Test
  void testConstructorWithoutArguments() {
    // GIVEN: No arguments are provided to the constructor.
    // WHEN: The constructor is called.
    // THEN: A runtime exception is created without a message.
    ServiceUnavailable ex = new ServiceUnavailable();
    assertNotNull(ex);
    instanceof(RuntimeException.class, ex);
  }

  @Test
  void testConstructorWithMessage() {
    // GIVEN: A message is provided to the constructor.
    String msg = "Service is temporarily unavailable.";
    // WHEN: The constructor is called with the message.
    // THEN: A runtime exception is created with the message.
    ServiceUnavailable ex = new ServiceUnavailable(msg);
    assertNotNull(ex);
    instanceof(RuntimeException.class, ex);
    assertEquals(msg, ex.getMessage());
  }

  @Test
  void testConstructorWithThrowable() {
    // GIVEN: A throwable is provided to the constructor.
    Throwable cause = new RuntimeException("Something went wrong");
    ServiceUnavailable ex = new ServiceUnavailable(cause);
    assertNotNull(ex);
    instanceof(RuntimeException.class, ex);
    assertSame(cause, ex.getCause());
  }

  @Test
  void testConstructorWithMessageAndThrowable() {
    // GIVEN: A message and a throwable are provided to the constructor.
    String msg = "Service unavailable.";
    Throwable cause = new RuntimeException("Detailed error message");
    ServiceUnavailable ex = new ServiceUnavailable(msg, cause);
    assertNotNull(ex);
    instanceof(RuntimeException.class, ex);
    assertEquals(msg, ex.getMessage());
    assertSame(cause, ex.getCause());
  }
}