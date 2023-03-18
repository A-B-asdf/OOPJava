package workflow;

import workflow.Commands.AbstractCommand;
import workflow.Commands.PrintingResult;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.*;

public class Calculator {
    static Logger LOGGER;
    static {
        try (InputStream is = Calculator.class.getResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(is);
            LOGGER = Logger.getLogger(Calculator.class.getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }
    private ExecutionContext context = new ExecutionContext();
    private CommandFactory factory = new CommandFactory();

    public void run(InputStream inputStream) {
        LOGGER.info("Calculator started");
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            if (parts.length == 0) {
                continue;
            }
            String commandName = parts[0];
            String[] params = Arrays.copyOfRange(parts, 1, parts.length);

            AbstractCommand command = factory.getCommand(commandName);
            try {
                LOGGER.info("Executing command " + commandName + " with parameters " + Arrays.toString(params));
                if (command instanceof PrintingResult) {
                    ((PrintingResult) command).SetPrintStream(System.out);
                }
                command.execute(context, params);
            } catch (workflow.exception.InvalidParameterException | workflow.exception.EmptyStackException e) {
                LOGGER.warning("Error executing command " + commandName + " with parameters " + Arrays.toString(params)
                        + ": " + e.getMessage());
                System.err.println(e.getMessage());
            } catch (Exception e) {
                LOGGER.warning("Error executing command " + commandName + " with parameters " + Arrays.toString(params)
                        + ": " + e.toString());
                System.err.println("Error executing command: " + e.toString());
            }

            LOGGER.info(context.toString());
        }
        scanner.close();
    }

    public ExecutionContext getContext() {
        return this.context;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        if (args.length == 1) {
            try (InputStream inputStream = new FileInputStream(args[0])) {
                calculator.run(inputStream);
            } catch (IOException e) {
                System.err.println("Error reading from file: " + e.getMessage());
                return;
            }
        } else {
            calculator.run(System.in);
        }
    }
}
