package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.service.ResultService;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunTestCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunTestCommand.class);
    private static final long serialVersionUID = 166914390870487363L;
    private TestService testService;
    private QuestionService questionService;
    private AnswerService answerService;
    private ResultService resultService;

    public RunTestCommand(TestService testService, QuestionService questionService, AnswerService answerService,
                          ResultService resultService) {
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.resultService = resultService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        Result result = new Result();
        HttpSession session = request.getSession();
        Map<Question, List<Answer>> questAnsMap = new HashMap<>();

        if (StringUtils.isEmpty(request.getParameter(Parameter.TEST_ID))) {
            throw new AppException("Invalid input");
        }

        long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
        if (testId <= 0) {
            throw new AppException("Invalid input");
        }
        long userId = ((User) session.getAttribute(Parameter.USER)).getId();
        long resultId = createResult(result, testId, userId);

        Test test = testService.findById(testId);
        LOGGER.trace("Found in DB: test --> " + test);

        List<Question> questions = questionService.findByTestId(testId);
        LOGGER.trace("Found in DB: questions --> " + questions);

        Collections.shuffle(questions);

        for (Question question : questions) {
            List<Answer> answers = answerService.findByQuestionId(question.getId());
            LOGGER.trace("Found in DB: answers --> " + answers);

            Collections.shuffle(answers);
            questAnsMap.put(question, answers);
        }

        request.setAttribute(Parameter.TEST, test);
        LOGGER.trace("Set the request attribute: test --> " + test);

        request.setAttribute(Parameter.QUEST_ANS_MAP, questAnsMap);
        LOGGER.trace("Set the request attribute: questAnsMap --> " + questAnsMap);

        request.setAttribute(Parameter.START_TIME, result.getStartTime());
        LOGGER.trace("Set the request attribute: start time --> " + result.getStartTime());

        request.setAttribute(Parameter.RESULT_ID, resultId);
        LOGGER.trace("Set the request attribute: result id --> " + resultId);

        LOGGER.debug("Command finished");
        return Path.PAGE_TEST_FORM;
    }

    private long createResult(Result result, long testId, long userId) {
        result.setUserId(userId);
        result.setTestId(testId);
        result.setStartTime(System.currentTimeMillis());

        return resultService.create(result);
    }

}
