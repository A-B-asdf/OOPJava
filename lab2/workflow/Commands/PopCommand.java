package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exeption.*;

public class PopCommand implements Command {
    public void execute(ExecutionContext context, String... params) throws EmptyStackException, InvalidParameterException {
        if (params.length != 0) {
            throw new InvalidParameterException("Pop command doesn't require any parameters");
        }
        if (context.getStack().isEmpty()) {
            throw new EmptyStackException();
        }
        Double result = context.getStack().pop();
        context.getNamedParameters().put("_", result);
    }
}
