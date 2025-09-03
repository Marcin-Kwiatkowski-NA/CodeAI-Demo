package com.bestpractice.api;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

@Test
public class ApplicationGeneratedAiTests {

    @Test
    void mainMethodTest() {
        // GIVEN: Setup the SpringApplication instance
        // WHEN: The SpringApplication.run() method is called
        // THEN: The SpringApplication.run() method should return without throwing exceptions.
        SpringApplication.run(Application.class, new String[0]);
    }
}
