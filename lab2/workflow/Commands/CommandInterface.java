package workflow.Commands;

import workflow.ExecutionContext;

/**
 * The CommandInterface interface defines the execute() method, which executes a command with the
 * given ExecutionContext and parameters.
 */
public interface CommandInterface {
    void execute(ExecutionContext context, String... params) throws Exception;
}
