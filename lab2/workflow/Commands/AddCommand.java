package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exeption.*;

public class AddCommand implements Command {
    public void execute(ExecutionContext context, String... params) throws EmptyStackException, InvalidParameterException {
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
