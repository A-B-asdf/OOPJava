package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.*;

/**
 * Executes the Divide command by popping the top two values from the stack, dividing the first value
 * by the second value, and pushing the result back onto the stack.
 */
public class DivideCommand extends AbstractCommand {
    /**
     * @param context the ExecutionContext containing the stack and named parameters
     * @param params this command doesn't require any parameters
     * @throws NotEnoughOperandsException if there are less than two operands on the stack
     * @throws InvalidParameterException if there are any parameters passed to this command
     * @throws MathException if attempting to divide by zero
     */
    public void execute(ExecutionContext context, String... params)
            throws NotEnoughOperandsException, InvalidParameterException, MathException {
        if (params.length != 0) {
            throw new InvalidParameterException("Divide command doesn't require any parameters");
        }
        if (context.getStack().size() < 2) {
            throw new NotEnoughOperandsException();
        }
        Double b = context.getStack().pop();
        Double a = context.getStack().pop();
        if (b == 0) {
            context.getStack().push(a);
            context.getStack().push(b);
            throw new MathException("Cannot divide by zero");
        }
        Double result = a / b;
        context.getStack().push(result);
    }
}
