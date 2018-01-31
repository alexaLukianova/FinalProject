package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ShowSaveQuestionCommand extends Command {
    private static final long serialVersionUID = 151339009504643187L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        checkSessionSope(request);
        request.setAttribute(Parameter.TEST_ID, request.getParameter(Parameter.TEST_ID));
        request.setAttribute(Parameter.ANSWERS_NUMBER, ANSWERS_NUMBER);

        return Path.PAGE_ADD_NEW_QUESTION;
    }

    private void checkSessionSope(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (Objects.nonNull(session.getAttribute(Parameter.ERRORS))) {
            Map<String, String> errors = (Map<String, String>) session.getAttribute(Parameter.ERRORS);
            session.removeAttribute(Parameter.ERRORS);
            request.setAttribute(Parameter.ERRORS, errors);
        }
        if (Objects.nonNull(session.getAttribute(Parameter.QUESTION_ID))) {
            String questionId = (String) session.getAttribute(Parameter.QUESTION);
            session.removeAttribute(Parameter.QUESTION);
            request.setAttribute(Parameter.QUESTION, questionId);
        }
    }
}
