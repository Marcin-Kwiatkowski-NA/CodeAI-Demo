package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testMainMethodRunsSpringApplication() {
        // GIVEN: Prepare arguments
        String[] args = new String[]{"--test"};

        // WHEN: Run the main method and capture the context
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        // THEN: Verify that application context is not null and active
        assertThat(context).isNotNull();
        assertThat(context.isActive()).isTrue();

        // Cleanup
        context.close();
    }

    @Test
    void testApplicationClassHasSpringBootAnnotation() {
        // GIVEN: The Application class
        Class<?> clazz = Application.class;

        // WHEN: Retrieve annotations
        SpringBootApplication annotation = clazz.getAnnotation(SpringBootApplication.class);

        // THEN: Verify annotation presence and excluded configurations
        assertThat(annotation).isNotNull();
        assertThat(annotation.exclude()).contains(
                org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class
        );
    }

    @Test
    void testSpringApplicationRunCreatesContext() {
        // GIVEN: Arguments for SpringApplication
        String[] args = new String[]{};

        // WHEN: Run SpringApplication directly
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = app.run(args);

        // THEN: Verify context is active
        assertThat(context.isActive()).isTrue();

        // Cleanup
        context.close();
    }
}
