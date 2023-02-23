package workflow.Commands;

import workflow.ExecutionContext;

public abstract class AbstractCommand {
    abstract public void execute(ExecutionContext context, String... params) throws Exception;
}
