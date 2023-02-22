package workflow.Commands;

import java.util.*;

public class PopCommand implements Command {
    public void execute(Stack<Double> stack, Map<String, Double> context, String... params) {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Double result = stack.pop();
        context.put("_", result);
    }
}
