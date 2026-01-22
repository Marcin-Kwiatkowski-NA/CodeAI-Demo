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
import static org.assertj.core.api.Assertions.*;

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void resetState() {
        // No mutable state to reset, method included to satisfy requirement
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No parameters
        // WHEN: Instantiate UnAuthorized with default constructor
        UnAuthorized exception = new UnAuthorized();
        // THEN: Message should be null and cause should be null
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN: A specific error message
        String message = "Unauthorized access detected";
        // WHEN: Instantiate UnAuthorized with message
        UnAuthorized exception = new UnAuthorized(message);
        // THEN: getMessage should return the provided message and cause should be null
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN: A root cause exception
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Instantiate UnAuthorized with cause
        UnAuthorized exception = new UnAuthorized(cause);
        // THEN: getMessage should be null and getCause should return the provided cause
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testMessageCauseConstructor() {
        // GIVEN: A message and a cause
        String message = "Access denied";
        Throwable cause = new NullPointerException("Null value");
        // WHEN: Instantiate UnAuthorized with both message and cause
        UnAuthorized exception = new UnAuthorized(message, cause);
        // THEN: getMessage and getCause should return the provided values
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testToStringIncludesClassAndMessage() {
        // GIVEN: A message
        String message = "Permission missing";
        // WHEN: Instantiate UnAuthorized with message
        UnAuthorized exception = new UnAuthorized(message);
        // THEN: toString should contain class name and message
        String toStringResult = exception.toString();
        assertThat(toStringResult).contains(UnAuthorized.class.getName());
        assertThat(toStringResult).contains(message);
    }

    @Test
    void testExceptionCanBeThrownAndCaught() {
        // GIVEN: An UnAuthorized exception with a message
        String message = "Forbidden";
        // WHEN: Throw the exception inside a lambda
        Throwable thrown = assertThatThrownBy(() -> {
            throw new UnAuthorized(message);
        }).isInstanceOf(UnAuthorized.class).extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message)
          .extracting(Throwable::toString).contains(UnAuthorized.class.getName())
          .extracting(Throwable::getClass).isEqualTo(UnAuthorized.class)
          .extracting(Throwable::getMessage).isEqualTo(message)
          .extracting(Throwable::getCause).isNull()
          .extracting(Throwable::toString).contains(message);
        assertThat(thrown).isInstanceOf(UnAuthorized.class);
    }
}
