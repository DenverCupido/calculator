package com.dfc.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class ArithmeticService {

    public int doCalculation(String operator, int i, int j) {
        switch (operator) {
            case "+" -> {
                return addition(i, j);
            }
            case "-" -> {
                return subtraction(i, j);
            }
            case "*" -> {
                return multiplication(i, j);
            }
            case "/" -> {
                return division(i, j);
            }
        }

        throw new RuntimeException("Switch failed");
    }


    public int addition(int i, int j) {
        return i + j;
    }

    public int subtraction(int i, int j) {
        return i - j;
    }

    public int multiplication(int i, int j) {
        return i * j;
    }

    public int division(int i, int j) {
        return i / j;
    }
}
