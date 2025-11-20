package com.bestpractice.api;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = Application.class)
public class ApplicationGeneratedAiTests {

    private String[] args;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        args = new String[]{};
    }

    @Test
    void givenApplicationClass_whenMainMethodIsInvoked_thenApplicationStartsSuccessfully() {
        // GIVEN: Application class and empty args
        // No additional setup required

        // WHEN: Main method is invoked
        SpringApplication.run(Application.class, args);

        // THEN: Application starts successfully
        assertThat(SpringApplication.run(Application.class, args)).isNotNull();
    }
}