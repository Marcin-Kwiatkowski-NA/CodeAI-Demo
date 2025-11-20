package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup required
    }

    @Test
    void testMainMethod() {
        // GIVEN: Setting up the application arguments
        String[] args = new String[]{"--spring.main.banner-mode=off"};

        // WHEN: Running the application
        var context = SpringApplication.run(Application.class, args);

        // THEN: Verify the application context is initialized
        assertThat(context).isNotNull();
    }
}
