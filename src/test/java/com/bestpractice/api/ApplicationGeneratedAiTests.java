package com.bestpractice.api;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    private Application application;

    @BeforeEach
    void setUp() {
        application = Mockito.createMock(Application.class);
    }

    @Test
    void mainMethodShouldRunSpringApplication() {
        // GIVEN: A new instance of the Application class is created.
        // WHEN: The SpringApplication.run() method is called with the Application class.
        // THEN: The SpringApplication.run() method should be called with the Application class.
        Mockito.verify(application).run(Application.class, (String[])<>());
    }
}
