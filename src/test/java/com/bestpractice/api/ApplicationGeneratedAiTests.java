package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package com.bestpractice.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
    }

    @Test
    void givenApplicationClass_whenMainMethodIsInvoked_thenApplicationStartsSuccessfully() {
        // GIVEN: A valid set of arguments for the main method
        String[] args = {};

        // WHEN: The main method is invoked
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        // THEN: The application should start successfully
        assertThat(context).isNotNull();
        context.close(); // Ensure the context is closed after the test
    }
}
