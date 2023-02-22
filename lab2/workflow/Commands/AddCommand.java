package workflow.Commands;

import java.util.*;

public class AddCommand implements Command {
    public void execute(Stack<Double> stack, Map<String, Double> context, String... params) {
        if (stack.size() < 2) {
            throw new IllegalStateException("Not enough operands on stack");
        }
        Double b = stack.pop();
        Double a = stack.pop();
        Double result = a + b;
        stack.push(result);
    }
}
