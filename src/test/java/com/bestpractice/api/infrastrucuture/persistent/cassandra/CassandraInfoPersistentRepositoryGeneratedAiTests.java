package com.bestpractice.api.infrastrucuture.persistent.cassandra;

public class Calculator {

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

    // Method to add two numbers
    public static int add(int a, int b) {
        return a + b;
    }

    // Method to multiply two numbers
    public static int multiply(int a, int b) {
        return a * b;
    }

    // Method to divide two numbers (with error handling using try-catch)
    public static double divide(int a, int b) {
        try {
            if (b == 0) {
                throw new ArithmeticException("Cannot divide by zero.");
            }
            return (double) a / b;
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
            return Double.NaN;
        }
    }

    public static void main(String[] args) {
        // Test cases
        int num1 = 5;
        int num2 = 3;
        int num3 = 10;
        int num4 = 0; // Test division by zero

        // Test addition
        int sum = add(num1, num2);
        System.out.println("The sum of " + num1 + " and " + num2 + " is: " + sum);

        // Test multiplication
        int product = multiply(num1, num2);
        System.out.println("The product of " + num1 + " and " + num2 + " is: " + product);

        // Test division
        double quotient = divide(num1, num2);
        System.out.println("The quotient of " + num1 + " and " + num2 + " is: " + quotient);

        double quotient2 = divide(num1, num4); // Test division by zero
        System.out.println("The quotient of " + num1 + " and " + num4 + " is: " + quotient2);
    }
}
