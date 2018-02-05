package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put(CommandKeys.LOGIN, CommandFactory.getCommand(CommandKeys.LOGIN));
        commands.put(CommandKeys.LIST_USERS, CommandFactory.getCommand(CommandKeys.LIST_USERS));
        commands.put(CommandKeys.LIST_TESTS, CommandFactory.getCommand(CommandKeys.LIST_TESTS));
        commands.put(CommandKeys.DELETE_TEST, CommandFactory.getCommand(CommandKeys.DELETE_TEST));
        commands.put(CommandKeys.DELETE_QUESTION, CommandFactory.getCommand(CommandKeys.DELETE_QUESTION));
        commands.put(CommandKeys.SAVE_QUESTION, CommandFactory.getCommand(CommandKeys.SAVE_QUESTION));
        commands.put(CommandKeys.LOCK_USER, CommandFactory.getCommand(CommandKeys.LOCK_USER));
        commands.put(CommandKeys.RUN_TEST, CommandFactory.getCommand(CommandKeys.RUN_TEST));
        commands.put(CommandKeys.EVALUATE_RESULT, CommandFactory.getCommand(CommandKeys.EVALUATE_RESULT));
        commands.put(CommandKeys.SHOW_PROFILE, CommandFactory.getCommand(CommandKeys.SHOW_PROFILE));
        commands.put(CommandKeys.REGISTER, CommandFactory.getCommand(CommandKeys.REGISTER));
        commands.put(CommandKeys.LOGOUT, CommandFactory.getCommand(CommandKeys.LOGOUT));
        commands.put(CommandKeys.DELETE_USER, CommandFactory.getCommand(CommandKeys.DELETE_USER));
        commands.put(CommandKeys.SHOW_SAVE_FORM, CommandFactory.getCommand(CommandKeys.SHOW_SAVE_FORM));
        commands.put(CommandKeys.SHOW_EDIT_FORM, CommandFactory.getCommand(CommandKeys.SHOW_EDIT_FORM));
        commands.put(CommandKeys.UPDATE_TEST_FORM, CommandFactory.getCommand(CommandKeys.UPDATE_TEST_FORM));
        commands.put(CommandKeys.UPDATE_QUESTION_FORM, CommandFactory.getCommand(CommandKeys.UPDATE_QUESTION_FORM));
        commands.put(CommandKeys.ADD_TEST, CommandFactory.getCommand(CommandKeys.ADD_TEST));
        commands.put(CommandKeys.SAVE_NEW_TEST, CommandFactory.getCommand(CommandKeys.SAVE_NEW_TEST));
        commands.put(CommandKeys.SHOW_REGISTER, CommandFactory.getCommand(CommandKeys.SHOW_REGISTER));
        commands.put(CommandKeys.SHOW_RESULT, CommandFactory.getCommand(CommandKeys.SHOW_RESULT));
        commands.put(CommandKeys.NO_COMMAND, CommandFactory.getCommand(CommandKeys.NO_COMMAND));



//
//        commands.put("noCommand", new NoCommand());
//


        LOGGER.debug("Command container was successfully initialized");
        LOGGER.trace("Number of commands --> " + commands.size());
    }

    public static Command get(String commandName) {
        if (Objects.isNull(commandName) || !commands.containsKey(commandName)) {
            LOGGER.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
