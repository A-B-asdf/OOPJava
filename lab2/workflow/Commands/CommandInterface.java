package workflow.Commands;

import workflow.ExecutionContext;

/**
 * CommandInterface is an interface for command classes that defines the execute method, which
 * takes an ExecutionContext and a variable number of String parameters as arguments.
 */
public interface CommandInterface {
    void execute(ExecutionContext context, String... params) throws Exception;
}
