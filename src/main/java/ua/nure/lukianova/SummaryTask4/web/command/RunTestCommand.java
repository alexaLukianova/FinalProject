package ua.nure.lukianova.SummaryTask4.web.command;

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;

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
        Result result = new Result();
        HttpSession session = request.getSession();
        Map<Question, List<Answer>> questAnsMap = new HashMap<>();

        long testId = Long.valueOf(request.getParameter(TEST_ID));
        long userId = ((User) session.getAttribute(USER)).getId();
        long resultId = createResult(result, testId, userId);

        Test test = testService.findById(testId);

        List<Question> questions = questionService.findByTestId(testId);
        Collections.shuffle(questions);

        for (Question question : questions) {
            List<Answer> answers = answerService.findByQuestionId(question.getId());
            Collections.shuffle(answers);
            questAnsMap.put(question, answers);
        }

        request.setAttribute(TEST, test);
        request.setAttribute(QUEST_ANS_MAP, questAnsMap);
        request.setAttribute(START_TIME, result.getStartTime());
        request.setAttribute(RESULT_ID, resultId);

        return Path.PAGE_TEST_FORM;
    }

    private long createResult(Result result, long testId, long userId) {
        result.setUserId(userId);
        result.setTestId(testId);
        result.setStartTime(System.currentTimeMillis());

        return resultService.create(result);
    }

}
