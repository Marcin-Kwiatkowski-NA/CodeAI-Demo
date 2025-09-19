package com.bestpractice.api.common.exception;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * This test case is designed to verify the functionality of the
 * `calculateSum` method. It checks if the method correctly
 * calculates the sum of two integers.
 */
public class CalculateSumTest {

    /**
     * Test case to verify the sum of two positive integers.
     */
    @Test
    public void testSumPositiveIntegers() {
        int num1 = 5;
        int num2 = 10;
        int expectedSum = 15;
        int actualSum = calculateSum(num1, num2);
        assertEquals(expectedSum, actualSum);
    }

    /**
     * Test case to verify the sum of two negative integers.
     */
    @Test
    public void testSumNegativeIntegers() {
        int num1 = -5;
        int num2 = -10;
        int expectedSum = -15;
        int actualSum = calculateSum(num1, num2);
        assertEquals(expectedSum, actualSum);
    }

    /**
     * Test case to verify the sum of a positive and a negative integer.
     */
    @Test
    public void testSumPositiveAndNegativeInteger() {
        int num1 = 5;
        int num2 = -10;
        int expectedSum = -5;
        int actualSum = calculateSum(num1, num2);
        assertEquals(expectedSum, actualSum);
    }

    /**
     * Test case to verify the sum of zero and a positive integer.
     */
    @Test
    public void testSumZeroAndPositiveInteger() {
        int num1 = 0;
        int num2 = 10;
        int expectedSum = 10;
        int actualSum = calculateSum(num1, num2);
        assertEquals(expectedSum, actualSum);
    }

    /**
     * Test case to verify the sum of zero and a negative integer.
     */
    @Test
    public void testSumZeroAndNegativeInteger() {
        int num1 = 0;
        int num2 = -10;
        int expectedSum = -10;
        int actualSum = calculateSum(num1, num2);
        assertEquals(expectedSum, actualSum);
    }

    /**
     * Test case to verify the sum of two integers with zero as the result.
     */
    @Test
    public void testSumZeroSum() {
        int num1 = 0;
        int num2 = 0;
        int expectedSum = 0;
        int actualSum = calculateSum(num1, num2);
        assertEquals(expectedSum, actualSum);
    }

    /**
     * Helper method to calculate the sum of two integers.
     *
     * @param num1 The first integer.
     * @param num2 The second integer.
     * @return The sum of the two integers.
     */
    private int calculateSum(int num1, int num2) {
        return num1 + num2;
    }
}