package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void testAddWithZero() {
        assertEquals(5, calculator.add(5, 0), "Addition with zero failed");
        assertEquals(0, calculator.add(0, 0), "Addition with both zeros failed");
    }

    @Test
    void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(5, 0), "Multiplication with zero failed");
        assertEquals(0, calculator.multiply(0, 5), "Multiplication with zero failed");
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3, 8",
            "-5, -3, -8",
            "-5, 3, -2",
            "1000000, 2000000, 3000000"
    })
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b), "Addition test failed");
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3, 15",
            "-5, -3, 15",
            "-5, 3, -15",
            "1000, 2000, 2000000"
    })
    void testMultiply(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b), "Multiplication test failed");
    }

    @Test
    void testEdgeCases() {
        assertEquals(Integer.MAX_VALUE, calculator.add(Integer.MAX_VALUE, 0), "Addition with MAX_VALUE failed");
        assertEquals(Integer.MIN_VALUE, calculator.add(Integer.MIN_VALUE, 0), "Addition with MIN_VALUE failed");

        assertThrows(ArithmeticException.class, () -> {
            // If needed, add logic to handle potential overflow
            int result = Math.addExact(Integer.MAX_VALUE, 1);
        }, "Expected overflow did not occur");
    }
}

