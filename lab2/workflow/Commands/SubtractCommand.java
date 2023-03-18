package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.*;

/**
 * SubtractCommand is an implementation of the AbstractCommand that subtracts the top element on the stack
 * from the second top element and pushes the result back onto the stack.
 */
public class SubtractCommand extends AbstractCommand {
    public void execute(ExecutionContext context, String... params)
            throws NotEnoughOperandsException, InvalidParameterException {
        if (params.length != 0) {
            throw new InvalidParameterException("Subtruct command doesn't require any parameters");
        }
        if (context.getStack().size() < 2) {
            throw new NotEnoughOperandsException();
        }
        Double b = context.getStack().pop();
        Double a = context.getStack().pop();
        Double result = a - b;
        context.getStack().push(result);
    }
}
