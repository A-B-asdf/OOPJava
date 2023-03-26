package com.example.Commands;

import com.example.ExecutionContext;
import com.example.exception.*;

/**
 * AddCommand is an implementation of the AbstractCommand that adds the two top elements on the stack
 * and pushes the result back onto the stack.
 */
public class AddCommand extends AbstractCommand {
    public void execute(ExecutionContext context, String... params)
            throws EmptyStackException, InvalidParameterException {
        if (params.length != 0) {
            throw new InvalidParameterException("Add command doesn't require any parameters");
        }
        if (context.getStack().size() < 2) {
            throw new EmptyStackException();
        }
        Double b = context.getStack().pop();
        Double a = context.getStack().pop();
        Double result = a + b;
        context.getStack().push(result);
    }
}
