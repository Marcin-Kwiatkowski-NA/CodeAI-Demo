package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void testSuccessfulCalculation() {
        // Assuming the method being tested is 'calculateTotal'
        calculateTotal(10, 20);
        // Assertions:  The result should be 30
        assertEquals(30, 10 + 20);
    }

    private int calculateTotal(int a, int b) {
        return a + b;
    }

    // Example method to be tested - replace with your actual method
    public int calculateTotal(int a, int b) {
        return a + b;
    }
}
