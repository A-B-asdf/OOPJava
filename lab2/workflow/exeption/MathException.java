package workflow.exeption;

public class MathException extends Exception {
    public MathException(String message) {
        super("Math exception: " + message);
    }
}
