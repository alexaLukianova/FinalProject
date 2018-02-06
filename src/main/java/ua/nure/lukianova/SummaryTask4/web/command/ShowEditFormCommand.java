package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Entity;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShowEditFormCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowEditFormCommand.class);
    private static final long serialVersionUID = -1081702184669926042L;
    private TestService testService;
    private QuestionService questionService;
    private AnswerService answerService;

    public ShowEditFormCommand(TestService testService, QuestionService questionService, AnswerService answerService) {
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        if (Objects.nonNull(request.getParameter(Parameter.TEST_ID))) {
            long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
            if (testId <= 0) {
                throw new AppException("Invalid input");
            }
            Test test = testService.findById(testId);
            LOGGER.trace("Found in DB: test --> " + test);

            request.setAttribute(Parameter.QUEST_ANS_MAP, extractQuestionInfoFromDB(testId));

            request.setAttribute(Parameter.TEST_ID, testId);
            LOGGER.trace("Set the request attribute: test id --> " + testId);

            request.setAttribute(Parameter.TEST, test);
            LOGGER.trace("Set the request attribute: test --> " + test);
        }

        checkSessionScope(request);

        request.setAttribute(Parameter.ANSWERS_NUMBER, ANSWERS_COUNT);
        LOGGER.trace("Set the request attribute: answers number --> " + ANSWERS_COUNT);

        LOGGER.debug("Command finished");
        return Path.PAGE_EDIT_TEST;
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
                questionService.findByTestId(testId)
                        .stream()
                        .collect(Collectors.toMap(Function.identity(),
                                question -> answerService.findByQuestionId(question.getId())
                                , (a, b) -> a,
                                LinkedHashMap::new));

        return map;
    }

}
