package com.example.Commands;

import com.example.ExecutionContext;
import com.example.exception.*;

/**
 * DivideCommand is an implementation of the AbstractCommand that divides the second top element on the stack
 * by the top element and pushes the result back onto the stack. Throws a MathException if attempting to divide by zero.
 */
public class DivideCommand extends AbstractCommand {
    public void execute(ExecutionContext context, String... params)
            throws NotEnoughOperandsException, InvalidParameterException, MathException {
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
