package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit.jupiter.PowerMockExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.reset;

@ExtendWith(PowerMockExtension.class)
@PrepareForTest(SpringApplication.class)
public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        reset(SpringApplication.class);
    }

    @Test
    void testMainDoesNotThrow() {
        // GIVEN an empty array of arguments
        String[] args = {};

        // WHEN the main method is invoked
        // THEN it should not throw any exception
        assertDoesNotThrow(() -> Application.main(args));
    }

    @Test
    void testMainCallsSpringApplicationRun() {
        // GIVEN an empty array of arguments and a mocked SpringApplication
        String[] args = {};
        mockStatic(SpringApplication.class);

        // WHEN the main method is invoked
        Application.main(args);

        // THEN SpringApplication.run should be called with Application.class and the args
        verify(SpringApplication.class).run(Application.class, args);
    }
}
