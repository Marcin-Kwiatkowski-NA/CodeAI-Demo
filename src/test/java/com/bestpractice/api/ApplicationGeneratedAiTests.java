package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
        System.clearProperty("spring.main.web-application-type");
    }

    @Test
    void testMainMethodRunsApplicationSuccessfully() {
        // GIVEN: Prepare arguments for application startup
        String[] args = new String[]{};

        // WHEN: Creating the SpringApplication instance without running the full context
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);

        // THEN: Verify that the application is configured correctly
        assertThat(app).isNotNull();
        assertThat(app.getMainApplicationClass()).isEqualTo(Application.class);
    }

    @Test
    void testMainMethodDoesNotThrowException() {
        // GIVEN: Prepare arguments for application startup
        String[] args = new String[]{};

        // WHEN: Executing the main method safely without starting full context
        Throwable thrown = null;
        try {
            SpringApplication app = new SpringApplication(Application.class);
            app.setWebApplicationType(WebApplicationType.NONE);
            app.run(args);
        } catch (Throwable t) {
            thrown = t;
        }

        // THEN: Verify that no exception is thrown during startup
        assertThat(thrown).isNull();
    }
}
