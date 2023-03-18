package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.*;

/**
 * A command that pops the top value from the stack, takes the square root of the value, and pushes the
 * result back onto the stack.
 */
public class SqrtCommand extends AbstractCommand {
    /**
     * @param context the ExecutionContext containing the stack and named parameters
     * @param params this command doesn't require any parameters
     * @throws NotEnoughOperandsException if there are no operands on the stack
     * @throws InvalidParameterException if there are any parameters passed to this command
     * @throws MathException if attempting to take the square root of a negative number
     */
    public void execute(ExecutionContext context, String... params)
            throws NotEnoughOperandsException, InvalidParameterException, MathException {
        if (params.length != 0) {
            throw new InvalidParameterException("Sqrt command doesn't require any parameters");
        }
        if (context.getStack().isEmpty()) {
            throw new NotEnoughOperandsException();
        }
        if (context.getStack().peek() < 0) {
            throw new MathException("Cannot take square root of a negative number");
        }
        Double a = context.getStack().pop();
        Double result = Math.sqrt(a);
        context.getStack().push(result);
    }
}
