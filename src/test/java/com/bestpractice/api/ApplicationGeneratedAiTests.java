package com.bestpractice.api;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;

class ApplicationGeneratedAiTests {

    private Application application;

    @ExtendWith(ApplicationTestExtension.class)
    static class ApplicationTests {

        @BeforeEach
        static void setUp() {
            application = new Application();
        }

        @Test
        void mainMethodRuns() {
            // GIVEN: The application is instantiated.
            // WHEN: The SpringApplication.run() method is called.
            // THEN: The SpringApplication instance is returned.
            SpringApplication.run(Application.class, new String[0]);
        }
    }
}

class ApplicationTestExtension {
}
