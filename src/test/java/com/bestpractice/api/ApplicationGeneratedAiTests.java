package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit.jupiter.PowerMockExtension;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verifyStatic;

@ExtendWith({MockitoExtension.class, PowerMockExtension.class})
@PrepareForTest(SpringApplication.class)
class ApplicationGeneratedAiTests {

    @BeforeEach
    void setUp() {
        PowerMockito.mockStatic(SpringApplication.class);
    }

    @Test
    void testMainRunsSpringApplicationRun() {
        // GIVEN
        ConfigurableApplicationContext mockContext = PowerMockito.mock(ConfigurableApplicationContext.class);
        PowerMockito.when(SpringApplication.run(eq(Application.class), any(String[].class))).thenReturn(mockContext);

        // WHEN
        Application.main(new String[]{"arg1", "arg2"});

        // THEN
        PowerMockito.verifyStatic(SpringApplication.class);
        SpringApplication.run(eq(Application.class), any(String[].class));
    }

    @Test
    void testMainWithEmptyArgs() {
        // GIVEN
        ConfigurableApplicationContext mockContext = PowerMockito.mock(ConfigurableApplicationContext.class);
        PowerMockito.when(SpringApplication.run(eq(Application.class), any(String[].class))).thenReturn(mockContext);

        // WHEN
        Application.main(new String[]{});

        // THEN
        PowerMockito.verifyStatic(SpringApplication.class);
        SpringApplication.run(eq(Application.class), any(String[].class));
    }

    @Test
    void testMainPropagatesException() {
        // GIVEN
        PowerMockito.when(SpringApplication.run(eq(Application.class), any(String[].class)))
                .thenThrow(new RuntimeException("test exception"));

        // WHEN & THEN
        assertThatThrownBy(() -> Application.main(new String[]{"arg"}))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("test exception");
    }
}
