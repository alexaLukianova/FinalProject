package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.*;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class RunTestCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        long testId = Long.valueOf(request.getParameter("test_id"));
        Test test = getTestService().findById(testId);

        List<Question> questions = new ArrayList<>(getQuestionService().findByTestId(testId));

        Collections.shuffle(questions);

        Map<Question, List<Answer>> questionsAndAnswers = new HashMap<>();
        for (Question question : questions) {
            List<Answer> answers = new ArrayList<>(getAnswerService().findByQuestionId(question.getId()));
            Collections.shuffle(answers);
            questionsAndAnswers.put(question, answers);
        }


//        Map<Question, List<Answer>> questionsAndAnswers = questions.stream()
//                .collect(Collectors.toMap(Function.identity(),
//                        question -> getAnswerService().findById(question.getId())));

        request.setAttribute("test", test);
        request.setAttribute("questions", questions);
        request.setAttribute("questionsAndAnswers", questionsAndAnswers);
        long currentTime = System.currentTimeMillis();

        HttpSession session = request.getSession();
        long userId = ((User)session.getAttribute("user")).getId();
        Result result = new Result();
        result.setUserId(userId);
        result.setTestId(testId);
        result.setStartTime(currentTime);
        getResultService().create(result);

        request.setAttribute("time", currentTime);

        return Path.PAGE_TEST_FORM;
    }
}
