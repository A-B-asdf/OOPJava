package com.example.Commands;

import java.io.PrintStream;

import com.example.ExecutionContext;
import com.example.exception.*;

/**
 * PrintCommand is an implementation of the AbstractCommand that prints the top element of the stack
 * to the specified output stream without removing it from the stack.
 */
public class PrintCommand extends AbstractCommand implements PrintingResult {
    public PrintStream outStream;

    public void SetPrintStream(PrintStream outputStream) {
        outStream = outputStream;
    }

    public void execute(ExecutionContext context, String... params)
            throws EmptyStackException, InvalidParameterException {
        if (params.length != 0) {
            throw new InvalidParameterException("Pop command doesn't require any parameters");
        }
        if (context.getStack().isEmpty()) {
            throw new EmptyStackException();
        }
        outStream.println(context.getStack().peek());
    }
}
