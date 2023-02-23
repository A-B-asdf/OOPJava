package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.*;

public class PopCommand extends AbstractCommand implements CommandInterface, ResultIngInterface {
    private Double result;

    public void execute(ExecutionContext context, String... params)
            throws EmptyStackException, InvalidParameterException {
        if (params.length != 0) {
            throw new InvalidParameterException("Pop command doesn't require any parameters");
        }
        if (context.getStack().isEmpty()) {
            throw new EmptyStackException();
        }
        result = context.getStack().pop();
    }

    public Double getResult() {
        return result;
    }
}
