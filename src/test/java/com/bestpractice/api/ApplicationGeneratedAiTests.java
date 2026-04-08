package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

public class ApplicationGeneratedAiTests {

    private String[] args;

    @BeforeEach
    void setUp() {
        args = new String[]{};
    }

    @Test
    void testMainMethodRunsSpringApplication() {
        // GIVEN - setup mock for SpringApplication.run
        try (var mockedSpringApp = mockStatic(SpringApplication.class)) {
            ConfigurableApplicationContext mockContext = mock(ConfigurableApplicationContext.class);
            mockedSpringApp.when(() -> SpringApplication.run(Application.class, args)).thenReturn(mockContext);

            // WHEN - invoking main method
            Application.main(args);

            // THEN - verify SpringApplication.run was called once and context is not null
            mockedSpringApp.verify(() -> SpringApplication.run(Application.class, args), times(1));
            assertThat(mockContext).isNotNull();
        }
    }
}
