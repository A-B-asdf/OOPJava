package workflow.Commands;

import java.util.*;

public interface Command {
    void execute(Stack<Double> stack, Map<String, Double> context, String... params) throws Exception ;
}