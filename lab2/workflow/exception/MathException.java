package workflow.exception;

/**
 * Thrown when a math exception occurs during command execution.
 */
public class MathException extends CalculatorException {
    /**
     * Constructs a new MathException with the specified message.
     *
     * @param message the message for the exception
     */
    public MathException(String message) {
        super("Math exception: " + message);
    }
}