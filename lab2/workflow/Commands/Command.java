package workflow.Commands;

import workflow.ExecutionContext;

public interface Command {
    void execute(ExecutionContext context, String... params) throws Exception;
}
