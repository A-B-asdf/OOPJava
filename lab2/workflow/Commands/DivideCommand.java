package workflow.Commands;

import java.util.*;

public class DivideCommand implements Command {
    public void execute(Stack<Double> stack, Map<String, Double> context, String... params) {
        if (stack.size() < 2) {
            throw new IllegalStateException("Not enough operands on stack");
        }
        Double b = stack.pop();
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        Double a = stack.pop();
        Double result = a / b;
        stack.push(result);
    }
}
