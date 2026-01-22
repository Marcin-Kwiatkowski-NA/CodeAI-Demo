package com.bestpractice.api;

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
import static org.assertj.core.api.Assertions.assertThatCode;

class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for this test class
    }

    @Test
    void testMainRunsWithoutException() {
        // GIVEN: an empty array of command line arguments
        String[] args = new String[0];

        // WHEN: the main method is invoked
        // THEN: it should complete without throwing any exception
        assertThatCode(() -> Application.main(args))
                .doesNotThrowAnyException();
    }
}
