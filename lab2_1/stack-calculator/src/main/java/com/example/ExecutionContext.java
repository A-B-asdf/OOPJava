package com.example;

import java.util.*;

public class ExecutionContext {
    private Stack<Double> stack;
    private Map<String, Double> namedParameters;

    public ExecutionContext() {
        this.stack = new Stack<Double>();
        this.namedParameters = new HashMap<>();
    }

    public Stack<Double> getStack() {
        return stack;
    }

    public Map<String, Double> getNamedParameters() {
        return namedParameters;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: ");
        sb.append(stack.toString());
        sb.append("\nNamed Parameters: ");
        sb.append(namedParameters.toString());
        return sb.toString();
    }
}
