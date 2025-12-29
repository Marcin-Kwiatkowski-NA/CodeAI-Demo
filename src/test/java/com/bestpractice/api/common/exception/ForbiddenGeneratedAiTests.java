package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org. junit. jupiter. api. Assertions. assertEquals;

import org. junit. jupiter. api. DisplayName;
import org. junit. jupiter. api. Test;

public class ForbiddenGeneratedAiTests {

    @Test
    @DisplayName("Test for Forbidden Exception")
    void testForbiddenException() {
        // Arrange
        String expectedMessage = "Forbidden";
        // Act
        // Assuming there is a ForbiddenException class with a constructor that takes a message
        ForbiddenException exception = new ForbiddenException(expectedMessage);
        // Assert
        assertEquals(expectedMessage, exception. getMessage());
    }
}

class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
