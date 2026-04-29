package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testMainMethodRunsApplicationSuccessfully() {
        // GIVEN: Application class setup

        // WHEN: Run the main method
        Application.main(new String[]{});

        // THEN: Verify that the application class is not null
        assertThat(Application.class).isNotNull();
    }

    @Test
    void testApplicationClassHasValidAnnotations() {
        // GIVEN: The Application class
        Class<?> clazz = Application.class;

        // WHEN: Retrieve annotations
        SpringBootApplication annotation = clazz.getAnnotation(SpringBootApplication.class);

        // THEN: Verify annotation presence and exclusions
        assertThat(annotation).isNotNull();
        assertThat(annotation.exclude()).containsExactlyInAnyOrder(
                org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class
        );
    }
}
