package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.*;

/**
 * Executes the addition operation with the given ExecutionContext and parameters.
 * Pops two operands from the stack, adds them, and pushes the result onto the stack.
 */
public class AddCommand extends AbstractCommand {
    public void execute(ExecutionContext context, String... params)
            throws EmptyStackException, InvalidParameterException {
        if (params.length != 0) {
            throw new InvalidParameterException("Add command doesn't require any parameters");
        }
        if (context.getStack().size() < 2) {
            throw new EmptyStackException();
        }
        Double b = context.getStack().pop();
        Double a = context.getStack().pop();
        Double result = a + b;
        context.getStack().push(result);
    }
}
