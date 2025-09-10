package com.bestpractice.api;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTestsGeneratedAiTests {

    @Test
    void mainMethod() {
        // GIVEN: The application is starting up.
        // WHEN: SpringApplication.run is called.
        // THEN: The Spring application context is initialized and started.
        SpringApplication.run(Application.class, new String[]{});
    }
}
