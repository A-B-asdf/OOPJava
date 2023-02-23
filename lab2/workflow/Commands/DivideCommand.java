package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exeption.*;

public class DivideCommand implements Command {
    public void execute(ExecutionContext context, String... params) throws NotEnoughOperandsException, InvalidParameterException, MathException {
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
