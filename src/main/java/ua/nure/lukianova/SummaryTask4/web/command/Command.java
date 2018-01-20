package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {


    private TestService testService;
    private QuestionService questionService;
    private AnswerService answerService;
    private UserService userService;

    {
        testService = new TestServiceImpl();
        questionService = new QuestionServiceImpl();
        answerService = new AnswerServiceImpl();
        userService = new UserServiceImpl();
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


}
