package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

class Solution {

    public static int[] findMissingNumbers(int[] nums) {
        List<Integer> missingNumbers = new java.util.ArrayList<>();
        Arrays.sort(nums);
        int current = 0;
        for (int num : nums) {
            if (num == current) {
                current++;
            } else if (num > current) {
                break;
            }
        }
        for (int i = current; i <= nums.length; i++) {
            missingNumbers.add(i);
        }
        return missingNumbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
