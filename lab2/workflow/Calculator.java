package workflow;

import workflow.Commands.Command;

import java.util.*;

public class Calculator {
    private ExecutionContext context = new ExecutionContext();
    private CalculatorFactory factory = new CalculatorFactory();

    public void run() {
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
                command.execute(context, params);
                // System.out.println(context.getStack());
            } catch (workflow.exeption.InvalidParameterException e) {
                System.err.println(e.getMessage());
            } catch (workflow.exeption.EmptyStackException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
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
