package workflow;

import workflow.Commands.Command;

import java.util.*;

public class Calculator {
    private Stack<Double> stack = new Stack<>();
    private Map<String, Double> context = new HashMap<>();
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
                command.execute(stack, context, params);
            } catch (Exception e) {
                System.err.println("Error executing command: " + e.getMessage());
            }

            System.out.println(stack);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }
}

