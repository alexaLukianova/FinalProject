package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.*;
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

public class RunTestCommand extends Command {
    private static final long serialVersionUID = 166914390870487363L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        Result result = new Result();
        HttpSession session = request.getSession();
        Map<Question, List<Answer>> questAnsMap = new HashMap<>();

        long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
        long userId = ((User) session.getAttribute(Parameter.USER)).getId();
        long resultId = createResult(result, testId, userId);

        Test test = getTestService().findById(testId);

        List<Question> questions = getQuestionService().findByTestId(testId);
        Collections.shuffle(questions);

        for (Question question : questions) {
            List<Answer> answers = getAnswerService().findByQuestionId(question.getId());
            Collections.shuffle(answers);
            questAnsMap.put(question, answers);
        }

        request.setAttribute(Parameter.TEST, test);
        request.setAttribute(Parameter.QUEST_ANS_MAP, questAnsMap);
        request.setAttribute(Parameter.START_TIME, result.getStartTime());
        request.setAttribute(Parameter.RESULT_ID, resultId);

        return Path.PAGE_TEST_FORM;
    }

    private long createResult(Result result, long testId, long userId) {
        result.setUserId(userId);
        result.setTestId(testId);
        result.setStartTime(System.currentTimeMillis());
        return getResultService().create(result);
    }

}
