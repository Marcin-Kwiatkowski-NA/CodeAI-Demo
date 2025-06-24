package com.bestpractice.api;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.WebMvcTest;

import static org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@ExtendWith(ApplicationTestsExtension.class)
@WebMvcTest(Application.class)
class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void mainMethod() {
        SpringApplication.run(Application.class, new String[]{});
    }
}
