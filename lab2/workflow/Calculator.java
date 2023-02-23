package workflow;

import workflow.Commands.Command;

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
    private CalculatorFactory factory = new CalculatorFactory();

    public void run() {
        LOGGER.info("Calculator started");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            if (parts.length == 0) {
                continue;
            }
            String commandName = parts[0];
            String[] params = Arrays.copyOfRange(parts, 1, parts.length);

            Command command = factory.getCommand(commandName);
            try {
                LOGGER.info("Executing command " + commandName + " with parameters " + Arrays.toString(params));
                command.execute(context, params);
            } catch (workflow.exeption.InvalidParameterException | workflow.exeption.EmptyStackException e) {
                LOGGER.warning("Error executing command " + commandName + " with parameters " + Arrays.toString(params)
                        + ": " + e.getMessage());
                System.err.println(e.getMessage());
            } catch (Exception e) {
                LOGGER.warning("Error executing command " + commandName + " with parameters " + Arrays.toString(params)
                        + ": " + e.toString());
                System.err.println("Error executing command: " + e.toString());
            }

            System.out.println(context);
        }
        scanner.close();
    }

    public ExecutionContext getContext() {
        return this.context;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }
}
