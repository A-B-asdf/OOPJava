package workflow.exception;

public class MathException extends CalculatorException {
    public MathException(String message) {
        super("Math exception: " + message);
    }
}