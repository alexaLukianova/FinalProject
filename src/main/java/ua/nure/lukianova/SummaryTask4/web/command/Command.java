package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {

    protected static final int ANSWERS_NUMBER = 4;
    protected static final String DELIMITER = "&";
    protected static final String EQUAL_SIGN = "=";


    private TestService testService;
    private QuestionService questionService;
    private AnswerService answerService;
    private UserService userService;


    private ResultService resultService;

    public Command()
    {
//        testService = new TestServiceImpl();
//        questionService = new QuestionServiceImpl();
//        answerService = new AnswerServiceImpl();
//        userService = new UserServiceImpl();
//        resultService = new ResultServiceImpl();
    }


    private static final long serialVersionUID = 520974220615423152L;

    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }


    protected TestService getTestService() {
        return testService;
    }

    protected QuestionService getQuestionService() {
        return questionService;
    }

    protected AnswerService getAnswerService() {
        return answerService;
    }

    protected UserService getUserService() {
        return userService;
    }

    protected ResultService getResultService() {
        return resultService;
    }

    protected void setTestService(TestService testService) {
        this.testService = testService;
    }

    protected void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    protected void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }
}
