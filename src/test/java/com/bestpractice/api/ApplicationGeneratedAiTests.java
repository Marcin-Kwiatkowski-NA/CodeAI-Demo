package com.bestpractice.api;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testMainMethodRunsSpringApplication() {
        // GIVEN: A mock of SpringApplication to intercept run invocation
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            mockedSpringApplication.when(() -> SpringApplication.run(any(Class.class), any(String[].class)))
                    .thenReturn(null);

            // WHEN: The main method is invoked
            Application.main(new String[]{});

            // THEN: Verify that SpringApplication.run was called with any arguments
            mockedSpringApplication.verify(() -> SpringApplication.run(any(Class.class), any(String[].class)));
            assertThat(true).isTrue(); // Basic assertion to ensure test passes if verification succeeds
        }
    }

    @Test
    void testApplicationClassHasMainMethod() throws Exception {
        // GIVEN: The Application class
        Class<?> clazz = Application.class;

        // WHEN: Checking for the main method existence
        var method = clazz.getDeclaredMethod("main", String[].class);

        // THEN: The main method should exist and be public static
        assertThat(method).isNotNull();
        assertThat(java.lang.reflect.Modifier.isStatic(method.getModifiers())).isTrue();
        assertThat(java.lang.reflect.Modifier.isPublic(method.getModifiers())).isTrue();
    }
}