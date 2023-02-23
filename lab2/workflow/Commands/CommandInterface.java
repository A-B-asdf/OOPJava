package workflow.Commands;

import workflow.ExecutionContext;

public interface CommandInterface {
    void execute(ExecutionContext context, String... params) throws Exception;
}
