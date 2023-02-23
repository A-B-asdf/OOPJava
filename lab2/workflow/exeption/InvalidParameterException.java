package workflow.exeption;

public class InvalidParameterException extends Exception {
    public InvalidParameterException(String parameter) {
        super("Invalid parameter: " + parameter);
    }
}
