package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exception.InvalidParameterException;

public class PushCommand extends AbstractCommand {
    public void execute(ExecutionContext context, String... params) throws InvalidParameterException {
        if (params.length != 1) {
            throw new InvalidParameterException("Push command requires one parameter");
        }
        String param = params[0];
        if (context.getNamedParameters().containsKey(param)) {
            context.getStack().push(context.getNamedParameters().get(param));
        } else {
            try {
                context.getStack().push(Double.parseDouble(param));
            } catch (NumberFormatException e) {
                throw new InvalidParameterException(param);
            }
        }
    }
}
