package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.InvalidParameterException;

/**
 * Executes the define operation with the given ExecutionContext and parameters.
 * Defines a named parameter with the given value in the execution context.
 */
public class DefineCommand extends AbstractCommand {
    /**
     * @param context the execution context
     * @param params  the command parameters (name and value)
     * @throws InvalidParameterException if the command parameters are invalid
     */
    public void execute(ExecutionContext context, String... params) throws InvalidParameterException {
        if (params.length != 2) {
            throw new InvalidParameterException("Define command requires two parameters");
        }
        String name = params[0];
        Double value;
        try {
            value = Double.parseDouble(params[1]);
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Invalid value: " + params[1]);
        }
        context.getNamedParameters().put(name, value);
    }
}
