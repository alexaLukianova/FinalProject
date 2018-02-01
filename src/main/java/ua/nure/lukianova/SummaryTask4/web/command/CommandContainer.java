package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.dao.ConnectionFactory;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("listUsers", new ListUsersCommand());
        commands.put("listTests", new ListTestsCommand());
        commands.put("deleteTest", new DeleteTestCommand());


        commands.put("deleteQuestion", CommandFactory.createDeleteQuestionCommand());
        commands.put("saveQuestion", new SaveQuestionCommand());


        commands.put("lockUser", new LockUserCommand());

        commands.put("runTest", new RunTestCommand());
        commands.put("evaluateResult", new EvaluateResultCommand());
        commands.put("showProfile", new ShowProfileCommand());
        commands.put("register", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("deleteUser", new DeleteUserCommand());


        commands.put("showSaveForm", new ShowSaveQuestionCommand());
        commands.put("showEditForm", new ShowEditFormCommand());
        commands.put("updateTestForm", new UpdateTestInfoCommand());
        commands.put("updateQuestionForm", new UpdateQuestionInfoCommand());
        commands.put("addTest", new AddTestCommand());
        commands.put("saveNewTest", new SaveNewTestCommand());


        commands.put("showRegister", new ShowRegisterCommand());
        commands.put("showResult", new ShowResultCommand());


//        commands.put("logout", new LogoutCommand());
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
