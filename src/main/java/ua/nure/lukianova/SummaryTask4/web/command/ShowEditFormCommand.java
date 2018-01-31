package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Entity;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShowEditFormCommand extends Command {

    private static final long serialVersionUID = -1081702184669926042L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        if (Objects.nonNull(request.getParameter(Parameter.TEST_ID))) {
            long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
            Test test = getTestService().findById(testId);

            request.setAttribute(Parameter.QUEST_ANS_MAP, extractQuestionInfoFromDB(testId));
            request.setAttribute(Parameter.TEST_ID, testId);
            request.setAttribute(Parameter.TEST, test);
        }

        checkSessionScope(request);

        request.setAttribute(Parameter.ANSWERS_NUMBER, ANSWERS_NUMBER);

        return Path.PAGE_EDIT_TEST;
    }

    private boolean isNewTest(HttpServletRequest request) {
        return Objects.isNull(request.getParameter(Parameter.TEST_ID))
                || request.getParameter(Parameter.TEST_ID).trim().isEmpty() ;
    }

    private void checkSessionScope(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (Objects.nonNull(session.getAttribute(Parameter.ERRORS))) {
            Map<String, String> errors = (Map<String, String>) session.getAttribute(Parameter.ERRORS);
            session.removeAttribute(Parameter.ERRORS);
            request.setAttribute(Parameter.ERRORS, errors);
        }
        if (Objects.nonNull(session.getAttribute(Parameter.QUESTION_ID))) {
            String questionId = (String) session.getAttribute(Parameter.QUESTION_ID);
            session.removeAttribute(Parameter.QUESTION_ID);
            request.setAttribute(Parameter.QUESTION_ID, questionId);
        }
        if (Objects.nonNull(session.getAttribute(Parameter.TEST))) {
            Entity test = (Entity) session.getAttribute(Parameter.TEST);
            session.removeAttribute(Parameter.TEST);
            request.setAttribute(Parameter.TEST, test);
        }
    }


    private Map<Question, List<Answer>> extractQuestionInfoFromDB(long testId) {
        Map<Question, List<Answer>> map =
                getQuestionService().findByTestId(testId)
                        .stream()
                        .collect(Collectors.toMap(Function.identity(),
                                question -> getAnswerService().findByQuestionId(question.getId())
                                , (a, b) -> a,
                                LinkedHashMap::new));

        return map;
    }


}
