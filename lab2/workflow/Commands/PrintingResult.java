package workflow.Commands;

import java.io.PrintStream;

/**
 * PrintingResult interface provides a method to set the PrintStream for commands that produce output.
 */
public interface PrintingResult {
    public void SetPrintStream(PrintStream outStream);
}
