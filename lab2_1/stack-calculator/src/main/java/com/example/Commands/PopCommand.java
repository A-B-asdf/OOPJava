package com.example.Commands;

import java.io.PrintStream;

import com.example.ExecutionContext;
import com.example.exception.*;

/**
 * PopCommand is an implementation of the AbstractCommand that removes the top element from the stack
 * and prints it to the specified output stream.
 */
public class PopCommand extends AbstractCommand implements PrintingResult {
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
        outStream.println(context.getStack().pop());
    }
}
