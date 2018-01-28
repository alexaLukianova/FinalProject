package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteQuestionCommand extends Command {

    private static final int MIN_QUESTION_NUMBER = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        long questionId = Long.valueOf(request.getParameter(Parameter.QUESTION_ID));
        long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));

        if (getQuestionService().findByTestId(testId).size() > MIN_QUESTION_NUMBER) {
            getQuestionService().delete(questionId);
        }

        request.setAttribute(Parameter.TEST_ID, testId);

        return Path.COMMAND_EDIT_TEST;
    }
}
