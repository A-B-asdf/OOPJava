package workflow.Commands;

import java.util.*;

public class PushCommand implements Command {
    /*
     * This implementation reads a single parameter from the params array, and checks if it's a named variable in the context. 
     * If it is, it pushes its value onto the stack. 
     * Otherwise, it assumes the parameter is a numeric literal and parses it as a double.
     */
    public void execute(Stack<Double> stack, Map<String, Double> context, String... params) {
        if (params.length != 1) {
            throw new IllegalArgumentException("Push command requires one parameter");
        }
        String param = params[0];
        if (context.containsKey(param)) {
            stack.push(context.get(param));
        } else {
            stack.push(Double.parseDouble(param));
        }
    }
}
