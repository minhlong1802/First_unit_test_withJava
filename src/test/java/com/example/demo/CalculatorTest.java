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
            "5, 3, 15",          // Nhân hai số dương
            "-5, -3, 15",        // Nhân hai số âm
            "-5, 3, -15",        // Nhân một số âm và một số dương
            "0, 5, 0",           // Nhân với 0
            "5, 0, 0",           // Nhân với 0 ở phía khác
            "2147483647, 1, 2147483647", // Nhân với Integer.MAX_VALUE
            "-2147483648, 1, -2147483648" // Nhân với Integer.MIN_VALUE
    })
    void testMultiply(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b), "Multiplication test failed");
    }

    @Test
    void testMultiplyWithLargeNumbers() {
        assertEquals(2000000, calculator.multiply(1000, 2000), "Large number multiplication failed");
    }

    @Test
    void testMultiplyWithEdgeCases() {
        // Kiểm tra biên
        assertEquals(Integer.MIN_VALUE, calculator.multiply(Integer.MIN_VALUE, 1),
                "Multiplication with MIN_VALUE failed");
        assertEquals(Integer.MAX_VALUE, calculator.multiply(Integer.MAX_VALUE, 1),
                "Multiplication with MAX_VALUE failed");

        // Kiểm tra tràn số
        assertThrows(ArithmeticException.class, () -> {
            int result = Math.multiplyExact(Integer.MAX_VALUE, 2);
        }, "Expected overflow did not occur");
    }

    @ParameterizedTest
    @CsvSource({
            "1000000000, 2000000000, ArithmeticException", // Giá trị quá lớn gây tràn số
            "-1000000000, -2000000000, ArithmeticException" // Giá trị âm lớn gây tràn số
    })
    void testMultiplyWithOverflow(int a, int b, String expectedException) {
        assertThrows(ArithmeticException.class, () -> {
            int result = Math.multiplyExact(a, b);
        }, "Expected overflow did not occur");
    }
}


