package workflow.Commands;

import workflow.ExecutionContext;
import workflow.exeption.*;

public interface Command {
    void execute(ExecutionContext context, String... params) throws StackException, InvalidParameterException;
}
