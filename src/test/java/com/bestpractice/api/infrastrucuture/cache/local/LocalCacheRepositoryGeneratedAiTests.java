package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Solution {
    public int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}

class SolutionTest {
    @Test
    void testSumPositiveNumbers() {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4, 5};
        int expectedSum = 15;
        int actualSum = solution.sum(nums);
        assertEquals(expectedSum, actualSum);
    }

    @Test
    void testSumNegativeNumbers() {
        Solution solution = new Solution();
        int[] nums = {-1, -2, -3, -4, -5};
        int expectedSum = -15;
        int actualSum = solution.sum(nums);
        assertEquals(expectedSum, actualSum);
    }

    @Test
    void testSumMixedNumbers() {
        Solution solution = new Solution();
        int[] nums = {-1, 2, -3, 4, -5};
        int expectedSum = -3;
        int actualSum = solution.sum(nums);
        assertEquals(expectedSum, actualSum);
    }

    @Test
    void testEmptyArray() {
        Solution solution = new Solution();
        int[] nums = {};
        int expectedSum = 0;
        int actualSum = solution.sum(nums);
        assertEquals(expectedSum, actualSum);
    }
}
