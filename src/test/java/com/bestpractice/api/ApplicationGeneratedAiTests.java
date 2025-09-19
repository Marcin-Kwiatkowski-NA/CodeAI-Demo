package com.bestpractice.api;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.MockedMethod;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.mock.mockito.MockedMethod;

@ExtendWith(MockedMethod.class)
class ApplicationGeneratedAiTests {

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @Test
    void mainMethod() {
        // GIVEN: No preconditions set for the main method.
        // WHEN: SpringApplication.run() is called.
        // THEN: The Spring application context should be initialized and started.
        SpringApplication.run(Application.class, new String[0]);
    }
}
