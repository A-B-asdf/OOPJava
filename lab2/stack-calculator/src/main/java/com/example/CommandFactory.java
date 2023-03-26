package com.example;

import com.example.Commands.AbstractCommand;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

/**
 * A factory class for creating commands.
 */
public class CommandFactory {
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class.getName());

    /**
     * The map of command names to command classes.
     */
    private Map<String, Class<? extends AbstractCommand>> commandMap = new HashMap<>();

    /**
     * Creates a new CommandFactory and initializes the command map from a properties file.
     */
    public CommandFactory() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("commands");
            for (String commandName : bundle.keySet()) {
                String className = bundle.getString(commandName);
                Class<? extends AbstractCommand> commandClass = Class.forName(className)
                        .asSubclass(AbstractCommand.class);
                commandMap.put(commandName, commandClass);
                LOGGER.info("Added command mapping: " + commandName + " -> " + className);
            }
        } catch (MissingResourceException e) {
            LOGGER.severe("Error loading command mappings: " + e.getMessage());
        } catch (ClassNotFoundException | ClassCastException e) {
            LOGGER.severe("Error loading command mappings: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gets a command object for the specified command name.
     *
     * @param commandName the name of the command
     * @return a command object for the specified command name
     * @throws IllegalArgumentException if the command name is unknown
     */
    public AbstractCommand getCommand(String commandName) {
        Class<? extends AbstractCommand> commandClass = commandMap.get(commandName);
        if (commandClass == null) {
            throw new IllegalArgumentException("Unknown command: " + commandName);
        }
        try {
            return commandClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot create command: " + commandName, e);
        }
    }
}
