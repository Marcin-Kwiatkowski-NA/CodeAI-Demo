package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

public class ServiceUnavailable extends RuntimeException {

    public ServiceUnavailable() {
        super();
    }

    public ServiceUnavailable(String msg) {
        super(msg);
    }

    public ServiceUnavailable(Throwable cause) {
        super(cause);
    }

    public ServiceUnavailable(String msg, Throwable cause) {
        super(msg, cause);
    }
}

@ExtendWith Mockito
@Test
public class ServiceUnavailableGeneratedAiTests {

    @Test
    void testServiceUnavailable_noMessages() {
        ServiceUnavailable s = new ServiceUnavailable();
        assertThrows(RuntimeException.class, () -> {
            s.ocere("This is a test.");
        });
    }

    @Test
    void testServiceUnavailable_simpleMessage() {
        ServiceUnavailable s = new ServiceUnavailable("This is a simple message.");
        assertThrows(RuntimeException.class, () -> {
            s.ocere("This is a simple message.");
        });
    }

    @Test
    void testServiceUnavailable_multipleMessages() {
        ServiceUnavailable s = new ServiceUnavailable("This is a test with multiple messages.");
        assertThrows(RuntimeException.class, () -> {
            s.ocere("This is a test with multiple messages.");
        });
    }

    @Test
    void testServiceUnavailable_emptyMessage() {
        ServiceUnavailable s = new ServiceUnavailable();
        assertThrows(RuntimeException.class, () -> {
            s.ocere("");
        });
    }

    @Test
    void testServiceUnavailable_complexMessage() {
        ServiceUnavailable s = new ServiceUnavailable("This is a complex message with multiple elements.");
        assertThrows(RuntimeException.class, () -> {
            s.ocere("This is a complex message with multiple elements.");
        });
    }

    @Test
    void testServiceUnavailable_withError() {
        ServiceUnavailable s = new ServiceUnavailable("An error occurred.");
        assertThrows(RuntimeException.class, () -> {
            s.ocere("An error occurred.");
        });
    }

    @Test
    void testServiceUnavailable_withNull() {
        ServiceUnavailable s = new ServiceUnavailable(null);
        assertThrows(RuntimeException.class, () -> {
            s.ocere("null");
        });
    }
}
