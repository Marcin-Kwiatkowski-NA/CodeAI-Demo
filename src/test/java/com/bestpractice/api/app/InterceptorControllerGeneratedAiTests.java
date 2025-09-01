package com.bestpractice.api.app;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

@ExtendWith
public class AppTest {

    @BeforeAll
    static void setUpBeforeAllTests() {
        // Setup code here
    }

    @AfterAll
    static void tearDownAfterAllTests() {
        // Teardown code here
    }

    @BeforeEach
    void setUpBeforeEachTest() {
        // Setup code for each test
    }

    @AfterEach
    void tearDownAfterEachTest() {
        // Teardown code after each test
    }

    @Test
    void testMethod() {
        // Test code here
    }
}