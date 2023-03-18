package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.*;

public class MultiplyCommand extends AbstractCommand {
    public void execute(ExecutionContext context, String... params)
            throws NotEnoughOperandsException, InvalidParameterException {
        if (params.length != 0) {
            throw new InvalidParameterException("Multiply command doesn't require any parameters");
        }
        if (context.getStack().size() < 2) {
            throw new NotEnoughOperandsException();
        }
        Double b = context.getStack().pop();
        Double a = context.getStack().pop();
        Double result = a * b;
        context.getStack().push(result);
    }
}
