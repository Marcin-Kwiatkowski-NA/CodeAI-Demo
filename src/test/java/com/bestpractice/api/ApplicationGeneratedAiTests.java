package com.bestpractice.api;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationGeneratedAiTests {

    private String[] args;

    @BeforeEach
    void setUp() {
        args = new String[]{};
    }

    @Test
    void testMainShouldRunSpringApplicationSuccessfully() {
        // GIVEN: valid arguments and application class
        String[] localArgs = args;

        // WHEN: invoking the main method
        Application.main(localArgs);

        // THEN: verify that application class is loaded successfully
        assertThat(Application.class).isNotNull();
    }

    @Test
    void testMainShouldHandleEmptyArgsGracefully() {
        // GIVEN: empty arguments
        String[] emptyArgs = new String[]{};

        // WHEN: invoking the main method with empty args
        Application.main(emptyArgs);

        // THEN: verify that application class is loaded successfully
        assertThat(Application.class.getPackageName()).isEqualTo("com.bestpractice.api");
    }
}
