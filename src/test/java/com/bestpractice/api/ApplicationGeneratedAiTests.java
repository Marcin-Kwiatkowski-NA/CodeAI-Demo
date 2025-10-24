package com.bestpractice.api;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any state before each test
        // Ensure required environment variables or system properties are set to avoid null secrets
        System.setProperty("app.secret", "test-secret");
    }

    @Test
    void testMainMethodRunsWithoutExceptions() {
        // GIVEN: Prepare arguments for main method
        String[] args = new String[]{"--app.secret=test-secret"};

        // WHEN: Call the main method
        // THEN: Verify no exceptions are thrown
        assertDoesNotThrow(() -> {
            ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
            assertNotNull(context);
            context.close();
        });
    }

    @Test
    void testSpringApplicationRunReturnsContext() {
        // GIVEN: Prepare arguments for SpringApplication.run
        String[] args = new String[]{"--app.secret=test-secret"};

        // WHEN: Run the Spring application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        // THEN: Verify that the application context is not null
        assertNotNull(context);
        context.close();
    }
}
