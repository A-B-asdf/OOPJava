package workflow.exception;

/**
 * Thrown when a command is given an invalid parameters.
 */
public class InvalidParameterException extends CalculatorException {
    /**
     * Constructs a new InvalidParameterException with the specified parameter name.
     *
     * @param parameter the name of the invalid parameter
     */
    public InvalidParameterException(String parameter) {
        super("Invalid parameter: " + parameter);
    }
}
