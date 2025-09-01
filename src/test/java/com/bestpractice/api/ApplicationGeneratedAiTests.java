package com.bestpractice.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @Test
    void mainMethod() {
        // GIVEN: A new SpringApplication instance is created.
        // WHEN: SpringApplication.run() is called with the Application class.
        // THEN: The SpringBoot application starts and the main method completes without throwing exceptions.
        SpringApplication.run(Application.class, new String[]{});
    }
}
