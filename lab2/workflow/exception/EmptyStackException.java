package workflow.exception;

public class EmptyStackException extends CalculatorException {
    public EmptyStackException() {
        super("Stack is empty");
    }

    public EmptyStackException(String message) {
        super("Stack is empty. " + message);
    }
}