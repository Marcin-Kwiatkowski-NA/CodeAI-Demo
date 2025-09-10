package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;

public class Calculator {

    public static Double calculate_average(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return null;
        }

        try {
            double sum = 0;
            for (Double number : numbers) {
                sum += number;
            }
            if (numbers.size() == 0) {
                return null;
            }
            return sum / numbers.size();
        } catch (ArithmeticException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
