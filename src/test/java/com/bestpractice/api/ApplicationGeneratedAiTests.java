package com.bestpractice.api;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.Mock;
import static org.springframework.boot.test.mock.mockito.Mockito.*;

@ExtendWith(ApplicationTests.class)
class ApplicationGeneratedAiTests {

    SpringApplication springApplication;

    @BeforeEach
    void beforeEachTest() {
        // Reset any state modified during the test.
        Mockito.reset();
    }

    @Test
    void mainMethod() {
        // GIVEN: No preconditions
        // WHEN: SpringApplication.run(Application.class, args);
        // THEN: The application starts successfully.
        when(springApplication.run(new String[]{})).thenThrow(new RuntimeException());
    }
}

class ApplicationTests extends org.junit.jupiter.api.extension.InvocationMonitor {

    @BeforeEach
    void beforeEachTest() {
        // Reset any state modified during the test.
        Mockito.reset();
    }
}
