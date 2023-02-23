package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.*;

public class SqrtCommand extends AbstractCommand implements CommandInterface {
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
