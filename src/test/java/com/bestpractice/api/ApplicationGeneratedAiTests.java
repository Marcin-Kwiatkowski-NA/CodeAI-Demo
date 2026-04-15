package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testMainMethodRunsApplicationSuccessfully() {
        // GIVEN: Prepare arguments for the main method
        String[] args = new String[]{};

        // WHEN: Create a SpringApplication instance without starting full context
        SpringApplication app = new SpringApplication(com.bestpractice.api.Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);

        ConfigurableApplicationContext context = app.run(args);

        // THEN: Verify that the application context is not null and is active
        assertThat(context).isNotNull();
        assertThat(context.isActive()).isTrue();

        // Cleanup
        context.close();
    }

    @Test
    void testMainMethodHandlesEmptyArgsGracefully() {
        // GIVEN: Empty arguments
        String[] args = {};

        // WHEN: Create a SpringApplication instance with minimal configuration
        SpringApplication app = new SpringApplication(com.bestpractice.api.Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);

        ConfigurableApplicationContext context = app.run(args);

        // THEN: Verify that the context starts successfully
        assertThat(context).isNotNull();
        assertThat(context.isRunning()).isTrue();

        // Cleanup
        context.close();
    }
}
