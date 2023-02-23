package workflow.exception;

public class NotEnoughOperandsException extends CalculatorException {
    public NotEnoughOperandsException() {
        super("Not enough operands on stack");
    }

    public NotEnoughOperandsException(String additionalMessage) {
        super("Not enough operands on stack. " + additionalMessage);
    }
}
