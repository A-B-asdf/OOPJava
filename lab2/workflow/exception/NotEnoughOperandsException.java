package workflow.exception;

/**
 * Thrown when there are not enough operands on the stack for a command that requires them.
 */
public class NotEnoughOperandsException extends CalculatorException {
    /**
     * Constructs a new NotEnoughOperandsException.
     */
    public NotEnoughOperandsException() {
        super("Not enough operands on stack");
    }

    /**
     * Constructs a new NotEnoughOperandsException with an additional message.
     *
     * @param additionalMessage an additional message to include in the exception
     */
    public NotEnoughOperandsException(String additionalMessage) {
        super("Not enough operands on stack. " + additionalMessage);
    }
}
