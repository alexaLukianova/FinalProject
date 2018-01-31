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
    private long testId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        long questionId = Long.valueOf(request.getParameter(Parameter.QUESTION_ID));
        testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));

        if (getQuestionService().findByTestId(testId).size() > MIN_QUESTION_NUMBER) {
            getQuestionService().delete(questionId);
        }

        return getURL();
    }

    private String getURL() {
        StringBuilder url = new StringBuilder(Path.COMMAND_SHOW_EDIT_FORM);
        url.append(DELIMITER).append(Parameter.TEST_ID).append(EQUAL_SIGN).append(testId);
        return url.toString();
    }
}
