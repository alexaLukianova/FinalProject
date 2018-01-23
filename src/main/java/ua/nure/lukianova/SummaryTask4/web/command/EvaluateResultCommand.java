package ua.nure.lukianova.SummaryTask4.web.command;

import com.sun.org.apache.regexp.internal.RE;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class EvaluateResultCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {


        long testId = Long.valueOf(request.getParameter("test_id"));


        String[] questions = request.getParameterValues("question");


        request.getParameterValues("answer");

        String[] answers = request.getParameterValues("correct");
        String[] value = null;


        Map<Long, TreeSet<Long>> map = new HashMap<>();
        TreeSet<Long> chosenAnswers = new TreeSet<>();

        long questionId;
        long answerId;
        for (String answer : answers) {
            value = answer.split("&");
            questionId = Long.valueOf(value[0]);
            answerId = Long.valueOf(value[1]);
            if (map.containsKey(questionId)) {
                map.get(questionId).add(answerId);
            } else {
                chosenAnswers.add(answerId);
                map.put(questionId, (TreeSet<Long>) chosenAnswers.clone());
                chosenAnswers.clear();
            }
        }


        int totalNumberOfQuestions = getQuestionService().findByTestId(testId).size();

        int correctAnsweres = 0;


        for (Map.Entry<Long, TreeSet<Long>> entry : map.entrySet()) {
            TreeSet<Long> correct = getIdOfCorrectAnswers(entry.getKey());

            if (correct.size() == entry.getValue().size()) {
                if (correct.containsAll(entry.getValue())) {
                    correctAnsweres++;
                }
            }

        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Result result = getResultService().findByUserAndTestId(user.getId(), testId);
        int mark = correctAnsweres * 100 / totalNumberOfQuestions;
        result.setResult(mark);

        getResultService().update(result.getId(), result.getResult());

        request.setAttribute("message", "Your score is " + mark + "%.");

        return Path.PAGE_MESSAGE;

    }

    private TreeSet<Long> getIdOfCorrectAnswers(long id) {
        List<Answer> answers = getAnswerService().findCorrectByQuestionId(id);
        TreeSet<Long> answersId = new TreeSet<>();
        for (Answer answer : answers) {
            answersId.add(answer.getId());
        }
        return answersId;
    }


}
