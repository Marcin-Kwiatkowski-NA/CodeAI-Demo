package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable serviceUnavailable;

    @BeforeEach
    void setUp() {
        serviceUnavailable = new ServiceUnavailable();
    }

    @Test
    void shouldCreateServiceUnavailableWithNoArguments() {
        // GIVEN
        // WHEN
        // THEN
        assertNotNull(serviceUnavailable);
    }

    @Test
    void shouldCreateServiceUnavailableWithMessage() {
        // GIVEN
        String message = "Service is unavailable";
        // WHEN
        ServiceUnavailable serviceUnavailableWithMessage = new ServiceUnavailable(message);
        // THEN
        assertNotNull(serviceUnavailableWithMessage);
        assertEquals(message, serviceUnavailableWithMessage.getMessage());
    }

    @Test
    void shouldCreateServiceUnavailableWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Cause of the exception");
        // WHEN
        ServiceUnavailable serviceUnavailableWithCause = new ServiceUnavailable(cause);
        // THEN
        assertNotNull(serviceUnavailableWithCause);
        assertEquals(cause, serviceUnavailableWithCause.getCause());
    }

    @Test
    void shouldCreateServiceUnavailableWithMessageAndCause() {
        // GIVEN
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Cause of the exception");
        // WHEN
        ServiceUnavailable serviceUnavailableWithMessageAndCause = new ServiceUnavailable(message, cause);
        // THEN
        assertNotNull(serviceUnavailableWithMessageAndCause);
        assertEquals(message, serviceUnavailableWithMessageAndCause.getMessage());
        assertEquals(cause, serviceUnavailableWithMessageAndCause.getCause());
    }
}
