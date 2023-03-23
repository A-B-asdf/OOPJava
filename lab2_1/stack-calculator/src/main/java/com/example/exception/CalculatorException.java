package com.example.exception;

/**
 * The base class for exceptions thrown by the calculator.
 */
public class CalculatorException extends Exception {
    /**
     * Constructs a new CalculatorException with the specified message.
     *
     * @param message the message for the exception
     */
    public CalculatorException(String message) {
        super(message);
    }

    /**
     * Constructs a new CalculatorException with the specified message and cause.
     *
     * @param message the message for the exception
     * @param cause the cause of the exception
     */
    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }
}