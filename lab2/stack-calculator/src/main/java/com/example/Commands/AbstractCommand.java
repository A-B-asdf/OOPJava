package com.example.Commands;

import com.example.ExecutionContext;

/**
 * AbstractCommand is an abstract base class for commands that implements the CommandInterface.
 * It provides a common structure for command classes.
 */
public abstract class AbstractCommand implements CommandInterface {
    abstract public void execute(ExecutionContext context, String... params) throws Exception;
}
