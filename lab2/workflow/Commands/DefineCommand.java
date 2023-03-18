package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.InvalidParameterException;

/**
 * DefineCommand is an implementation of the AbstractCommand that associates a name with a numeric value
 * in the ExecutionContext's named parameters map.
 */
public class DefineCommand extends AbstractCommand {
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
