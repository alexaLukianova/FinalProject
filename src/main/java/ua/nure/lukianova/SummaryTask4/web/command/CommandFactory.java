package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.AnswerServiceImpl;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.service.QuestionServiceImpl;
import ua.nure.lukianova.SummaryTask4.service.ResultService;
import ua.nure.lukianova.SummaryTask4.service.ResultServiceImpl;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.service.TestServiceImpl;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.service.UserServiceImpl;
import ua.nure.lukianova.SummaryTask4.web.validator.AnswersValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.QuestionValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.TestValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.UserValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import static ua.nure.lukianova.SummaryTask4.web.command.CommandKeys.*;

public class CommandFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);

    private static QuestionService questionService;
    private static AnswerService answerService;
    private static TestService testService;
    private static UserService userService;
    private static ResultService resultService;
    private static Validator answerValidator;
    private static Validator questionValidator;
    private static Validator userValidator;
    private static Validator testValidator;

    static {
        questionService = new QuestionServiceImpl();
        answerService = new AnswerServiceImpl();
        testService = new TestServiceImpl();
        userService = new UserServiceImpl();
        resultService = new ResultServiceImpl();
        answerValidator = new AnswersValidator();
        questionValidator = new QuestionValidator();
        userValidator = new UserValidator();
        testValidator = new TestValidator();
    }

    public static Command getCommand(String commandName) {
        Command command = null;
        switch (commandName) {
            case LOGIN:
                command = new LoginCommand(userService);
                break;
            case LIST_USERS:
                command = new ListUsersCommand(userService);
                break;
            case LIST_TESTS:
                command = new ListTestsCommand(testService, questionService);
                break;
            case DELETE_TEST:
                command = new DeleteTestCommand(testService);
                break;
            case DELETE_QUESTION:
                command = new DeleteQuestionCommand(questionService);
                break;
            case SAVE_QUESTION:
                command = new SaveQuestionCommand(answerValidator, questionValidator, questionService, answerService);
                break;
            case LOCK_USER:
                command = new LockUserCommand(userService);
                break;
            case RUN_TEST:
                command = new RunTestCommand(testService, questionService, answerService, resultService);
                break;
            case EVALUATE_RESULT:
                command = new EvaluateResultCommand(resultService, testService, questionService, answerService);
                break;
            case SHOW_PROFILE:
                command = new ShowProfileCommand(resultService);
                break;
            case REGISTER:
                command = new RegisterCommand(userValidator, userService);
                break;
            case LOGOUT:
                command = new LogoutCommand();
                break;
            case DELETE_USER:
                command = new DeleteUserCommand(userService);
                break;
            case SHOW_SAVE_FORM:
                command = new ShowSaveQuestionCommand();
                break;
            case SHOW_EDIT_FORM:
                command = new ShowEditFormCommand(testService, questionService, answerService);
                break;
            case UPDATE_TEST_FORM:
                command = new UpdateTestInfoCommand(testService, testValidator);
                break;
            case UPDATE_QUESTION_FORM:
                command = new UpdateQuestionInfoCommand(answerValidator, questionValidator, questionService, answerService);
                break;
            case ADD_TEST:
                command = new AddTestCommand();
                break;
            case SAVE_NEW_TEST:
                command = new SaveNewTestCommand(testValidator, testService);
                break;
            case SHOW_REGISTER:
                command = new ShowRegisterCommand();
                break;
            case SHOW_RESULT:
                command = new ShowResultCommand();
                break;
        }
        return command;
    }
}
