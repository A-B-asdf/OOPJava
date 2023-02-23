package workflow.exeption;

public class NotEnoughOperandsException extends StackException {
    public NotEnoughOperandsException() {
        super("Not enough operands on stack");
    }

    public NotEnoughOperandsException(String additionalMessage) {
        super("Not enough operands on stack. " + additionalMessage);
    }
}
