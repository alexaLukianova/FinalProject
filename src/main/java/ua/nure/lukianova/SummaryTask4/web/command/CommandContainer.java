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
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("listUsers", new ListUsersCommand());
        commands.put("listTests", new ListTestsCommand());
        commands.put("addTest", new TestAddCommand());
        commands.put("addQuestions", new QuestionAddCommand());
        commands.put("saveTest", new SaveTestCommand());
        commands.put("deleteTest", new DeleteTestCommand());
        commands.put("editTest", new EditTestCommand());
        commands.put("addNewQuestion", new ShowAddQuestionForm());
        commands.put("updateTest", new UpdateTestCommand());
        commands.put("deleteQuestion", new DeleteQuestionCommand());
        commands.put("saveQuestion", new SaveQuestionCommand());
        commands.put("updateQuestion", new UpdateQuestionsCommand());
        commands.put("lockUser", new LockUserCommand());
        commands.put("runTest", new RunTestCommand());
        commands.put("evaluteResult", new EvaluateResultCommand());
        commands.put("showProfile", new ShowProfileCommand());
        commands.put("register", new RegisterCommand());
//        commands.put("logout", new LogoutCommand());
//        commands.put("viewSettings", new ViewSettingsCommand());
//        commands.put("noCommand", new NoCommand());
//
//        // client commands
//        commands.put("listMenu", new ListMenuCommand());
//
//        // admin commands
//        commands.put("listOrders", new ListOrdersCommand());

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
