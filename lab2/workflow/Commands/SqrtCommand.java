package workflow.Commands;

import java.util.*;

import java.lang.Math;

public class SqrtCommand implements Command {
    public void execute(Stack<Double> stack, Map<String, Double> context, String... params) {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Double a = stack.pop();
        if (a < 0) {
            throw new IllegalArgumentException("Cannot take square root of negative number");
        }
        Double result = Math.sqrt(a);
        stack.push(result);
    }
}
