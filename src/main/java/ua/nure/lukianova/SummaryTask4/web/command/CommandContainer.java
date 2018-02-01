package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.dao.ConnectionFactory;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static ua.nure.lukianova.SummaryTask4.web.command.CommandFactory.*;
import static ua.nure.lukianova.SummaryTask4.web.command.CommandKeys.*;

public class CommandContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        commands.put(LOGIN, getCommand(LOGIN));
        commands.put(LIST_USERS, getCommand(LIST_USERS));
        commands.put(LIST_TESTS, getCommand(LIST_TESTS));
        commands.put(DELETE_TEST, getCommand(DELETE_TEST));
        commands.put(DELETE_QUESTION, getCommand(DELETE_QUESTION));
        commands.put(SAVE_QUESTION, getCommand(SAVE_QUESTION));
        commands.put(LOCK_USER, getCommand(LOCK_USER));
        commands.put(RUN_TEST, getCommand(RUN_TEST));
        commands.put(EVALUATE_RESULT, getCommand(EVALUATE_RESULT));
        commands.put(SHOW_PROFILE, getCommand(SHOW_PROFILE));
        commands.put(REGISTER, getCommand(REGISTER));
        commands.put(LOGOUT, getCommand(LOGOUT));
        commands.put(DELETE_USER, getCommand(DELETE_USER));
        commands.put(SHOW_SAVE_FORM, getCommand(SHOW_SAVE_FORM));
        commands.put(SHOW_EDIT_FORM, getCommand(SHOW_EDIT_FORM));
        commands.put(UPDATE_TEST_FORM, getCommand(UPDATE_TEST_FORM));
        commands.put(UPDATE_QUESTION_FORM, getCommand(UPDATE_QUESTION_FORM));
        commands.put(ADD_TEST, getCommand(ADD_TEST));
        commands.put(SAVE_NEW_TEST, getCommand(SAVE_NEW_TEST));
        commands.put(SHOW_REGISTER, getCommand(SHOW_REGISTER));
        commands.put(SHOW_RESULT, getCommand(SHOW_RESULT));



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
