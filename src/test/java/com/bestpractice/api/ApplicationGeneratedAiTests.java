package com.bestpractice.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.mockito.stubbing.Stubbing;

public class ApplicationGeneratedAiTests {

    private Application application;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        application = new Application();
    }

    @org.junit.jupiter.api.Test
    void mainMethod_runApplication() {
        // GIVEN: A new instance of the Application class is created.
        // WHEN: The SpringApplication.run() method is called to start the application.
        // THEN: The application should be started successfully, and the Spring context should be initialized.
        SpringApplication.run(Application.class, new String[]{});
    }
}
