package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit.jupiter.PowerMockExtension;
import org.springframework.boot.SpringApplication;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith({PowerMockExtension.class, MockitoExtension.class})
@PrepareForTest(SpringApplication.class)
class ApplicationGeneratedAiTests {

    private MockedStatic<SpringApplication> mockedSpringApplication;

    @BeforeEach
    void setUp() {
        if (mockedSpringApplication != null) {
            mockedSpringApplication.close();
        }
        mockedSpringApplication = Mockito.mockStatic(SpringApplication.class);
    }

    @Test
    void testMainRunsWithoutException() {
        // GIVEN
        String[] args = new String[]{"arg1", "arg2"};

        // WHEN
        assertThatCode(() -> Application.main(args))
                .doesNotThrowAnyException();

        // THEN
        mockedSpringApplication.verify(() -> SpringApplication.run(Application.class, args), times(1));
    }

    @Test
    void testMainCallsSpringApplicationRunWithCorrectParameters() {
        // GIVEN
        String[] args = new String[]{"test"};

        // WHEN
        Application.main(args);

        // THEN
        mockedSpringApplication.verify(() -> SpringApplication.run(Application.class, args), times(1));
    }

    @Test
    void testMainHandlesEmptyArguments() {
        // GIVEN
        String[] args = new String[0];

        // WHEN
        assertThatCode(() -> Application.main(args))
                .doesNotThrowAnyException();

        // THEN
        mockedSpringApplication.verify(() -> SpringApplication.run(Application.class, args), times(1));
    }
}
