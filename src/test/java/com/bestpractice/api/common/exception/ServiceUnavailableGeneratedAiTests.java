package com.bestpractice.api.common.exception;

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
import static org.assertj.core.api.Assertions.assertThat;

class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable serviceUnavailable;

    @BeforeEach
    void setUp() {
        serviceUnavailable = null; // Reset state before each test
    }

    @Test
    void givenNoArgsConstructor_whenInstantiated_thenObjectIsCreated() {
        // GIVEN: No preconditions

        // WHEN: Instantiating ServiceUnavailable using no-args constructor
        serviceUnavailable = new ServiceUnavailable();

        // THEN: Verify the object is created and has no message or cause
        assertThat(serviceUnavailable).isNotNull();
        assertThat(serviceUnavailable.getMessage()).isNull();
        assertThat(serviceUnavailable.getCause()).isNull();
    }

    @Test
    void givenMessageConstructor_whenInstantiated_thenMessageIsSet() {
        // GIVEN: A specific error message
        String errorMessage = "Service is unavailable";

        // WHEN: Instantiating ServiceUnavailable with a message
        serviceUnavailable = new ServiceUnavailable(errorMessage);

        // THEN: Verify the message is set correctly
        assertThat(serviceUnavailable).isNotNull();
        assertThat(serviceUnavailable.getMessage()).isEqualTo(errorMessage);
        assertThat(serviceUnavailable.getCause()).isNull();
    }

    @Test
    void givenCauseConstructor_whenInstantiated_thenCauseIsSet() {
        // GIVEN: A specific cause (Throwable)
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Instantiating ServiceUnavailable with a cause
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN: Verify the cause is set correctly
        assertThat(serviceUnavailable).isNotNull();
        assertThat(serviceUnavailable.getMessage()).isEqualTo(cause.toString());
        assertThat(serviceUnavailable.getCause()).isEqualTo(cause);
    }

    @Test
    void givenMessageAndCauseConstructor_whenInstantiated_thenMessageAndCauseAreSet() {
        // GIVEN: A specific error message and cause
        String errorMessage = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Instantiating ServiceUnavailable with a message and cause
        serviceUnavailable = new ServiceUnavailable(errorMessage, cause);

        // THEN: Verify the message and cause are set correctly
        assertThat(serviceUnavailable).isNotNull();
        assertThat(serviceUnavailable.getMessage()).isEqualTo(errorMessage);
        assertThat(serviceUnavailable.getCause()).isEqualTo(cause);
    }
}
