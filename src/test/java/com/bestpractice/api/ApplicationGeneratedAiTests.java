package com.bestpractice.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    private SpringApplication springApplication;

    @BeforeEach
    void setUp() {
        springApplication = new SpringApplication();
    }

    @Test
    void mainMethodTest() {
        // GIVEN: SpringApplication instance
        // WHEN: SpringApplication.run() is called
        // THEN: Verify that run() method is called with the correct arguments
        when(springApplication.run(Application.class, (String[]) new String[0]))
                .thenReturn(null);
        verify(springApplication, times(1)).run(Application.class, (String[]) new String[0]);
    }
}
