package com.bestpractice.api;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    private String[] args;

    @BeforeEach
    void setUp() {
        args = new String[]{};
    }

    @Test
    void testMainMethodRunsSpringApplication() {
        // GIVEN: Mock static SpringApplication.run to prevent actual Spring Boot startup
        try (MockedStatic<SpringApplication> mockedSpringApplication = Mockito.mockStatic(SpringApplication.class)) {
            mockedSpringApplication.when(() -> SpringApplication.run(Application.class, args)).thenReturn(null);

            // WHEN: Run the main method
            Application.main(args);

            // THEN: Verify that the static method was called and Application class is valid
            mockedSpringApplication.verify(() -> SpringApplication.run(Application.class, args));
            assertThat(Application.class).isNotNull();
        }
    }
}