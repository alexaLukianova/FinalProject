package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;

public class ShowSaveQuestionCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowSaveQuestionCommand.class);
    private static final long serialVersionUID = 151339009504643187L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        checkSessionScope(request);
        request.setAttribute(TEST_ID, request.getParameter(TEST_ID));
        request.setAttribute(ANSWERS_NUMBER, ANSWERS_COUNT);

        return Path.PAGE_ADD_NEW_QUESTION;
    }

    private void checkSessionScope(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (Objects.nonNull(session.getAttribute(ERRORS))) {
            Map<String, String> errors = (Map<String, String>) session.getAttribute(ERRORS);
            session.removeAttribute(ERRORS);
            request.setAttribute(ERRORS, errors);
        }
        if (Objects.nonNull(session.getAttribute(QUESTION))) {
            String questionId = (String) session.getAttribute(QUESTION);
            session.removeAttribute(QUESTION);
            request.setAttribute(QUESTION, questionId);
        }
    }
}
