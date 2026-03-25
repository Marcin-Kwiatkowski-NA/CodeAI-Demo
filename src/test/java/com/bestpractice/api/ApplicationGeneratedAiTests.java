package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
        System.clearProperty("spring.main.web-application-type");
    }

    @Test
    void testMainMethodRunsSpringApplication() {
        // GIVEN: Prepare arguments
        String[] args = new String[]{};

        // WHEN: Run the application main method
        Throwable thrown = null;
        try {
            Application.main(args);
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN: Verify no exception is thrown
        assertThat(thrown).isNull();
    }

    @Test
    void testMainMethodHandlesEmptyArgsGracefully() {
        // GIVEN: Empty arguments
        String[] args = new String[]{};

        // WHEN: Run the application with empty args
        Throwable thrown = null;
        try {
            Application.main(args);
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN: Verify no exception is thrown
        assertThat(thrown).isNull();
    }

    @Test
    void testMainMethodDoesNotThrowException() {
        // GIVEN: Valid arguments
        String[] args = new String[]{"--spring.main.banner-mode=off"};

        // WHEN: Execute main method
        Throwable thrown = null;
        try {
            Application.main(args);
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN: Ensure no exception is thrown
        assertThat(thrown).isNull();
    }
}
