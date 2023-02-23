package workflow.exeption;

public class EmptyStackException extends StackException {
    public EmptyStackException() {
        super("Stack is empty");
    }

    public EmptyStackException(String message) {
        super("Stack is empty. " + message);
    }
}
