package workflow.Commands;

import java.util.*;

public class DefineCommand implements Command {
    public void execute(Stack<Double> stack, Map<String, Double> context, String... params) {
        if (params.length != 2) {
            throw new IllegalArgumentException("Define command requires two parameters");
        }
        String name = params[0];
        Double value = Double.parseDouble(params[1]);
        context.put(name, value);
    }
}
