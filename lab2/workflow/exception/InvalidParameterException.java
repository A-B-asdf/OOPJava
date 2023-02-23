package workflow.exception;

public class InvalidParameterException extends CalculatorException {
    public InvalidParameterException(String parameter) {
        super("Invalid parameter: " + parameter);
    }
}