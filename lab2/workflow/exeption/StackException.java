package workflow.exeption;

public class StackException extends Exception {
    public StackException(String message) {
        super("Stack exception: " + message);
    }
}
