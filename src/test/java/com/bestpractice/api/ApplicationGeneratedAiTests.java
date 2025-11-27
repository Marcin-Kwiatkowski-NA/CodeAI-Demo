package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No specific setup required for this test class
    }

    @Test
    void givenApplicationClass_whenMainMethodIsInvoked_thenApplicationStartsSuccessfully() {
        // GIVEN: A valid set of arguments for the main method
        String[] args = {};

        // WHEN: The main method is invoked
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        // THEN: The application context should be initialized successfully
        assertThat(context).isNotNull();

        // Clean up the application context after the test
        context.close();
    }
}
