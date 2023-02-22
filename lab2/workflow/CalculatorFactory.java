package workflow;

import workflow.Commands.Command;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CalculatorFactory {
    /*
     * This class reads the commands.properties file, which should be located in the same directory as the CalculatorFactory class. 
     * This file maps command names to fully qualified class names that implement the Command interface.
     */
    private Map<String, Class<? extends Command>> commandMap = new HashMap<>();

    public CalculatorFactory() {
        try (InputStream is = CalculatorFactory.class.getResourceAsStream("commands.properties")) {
            Properties props = new Properties();
            props.load(is);
            for (String commandName : props.stringPropertyNames()) {
                String className = props.getProperty(commandName);
                Class<? extends Command> commandClass = Class.forName(className).asSubclass(Command.class);
                commandMap.put(commandName, commandClass);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Command getCommand(String commandName) {
        Class<? extends Command> commandClass = commandMap.get(commandName);
        if (commandClass == null) {
            throw new IllegalArgumentException("Unknown command: " + commandName);
        }
        try {
            return commandClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Cannot create command: " + commandName, e);
        }
    }
}
