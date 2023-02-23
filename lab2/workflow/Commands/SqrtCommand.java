package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exeption.*;

public class SqrtCommand implements Command {
    public void execute(ExecutionContext context, String... params) throws EmptyStackException, InvalidParameterException {
        if (params.length != 0) {
            throw new InvalidParameterException("Sqrt command doesn't require any parameters");
        }
        if (context.getStack().isEmpty()) {
            throw new EmptyStackException();
        }
        if (context.getStack().peek() < 0) {
            throw new IllegalArgumentException("Cannot take square root of a negative number");
        }
        Double a = context.getStack().pop();
        Double result = Math.sqrt(a);
        context.getStack().push(result);
    }
}
