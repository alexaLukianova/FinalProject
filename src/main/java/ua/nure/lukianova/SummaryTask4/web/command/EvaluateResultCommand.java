package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.*;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.exception.DBException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class EvaluateResultCommand extends Command {

    private static final String DELIMITER = "&";
    private static final long serialVersionUID = 8428428763878912055L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
        long resultId = Long.valueOf(request.getParameter(Parameter.RESULT_ID));
        String[] answers = request.getParameterValues(Parameter.ANSWER_CORRECT);

        Result result = getResultService().findById(resultId);
        Test test = getTestService().findById(testId);

        int totalNumberOfQuestions = getQuestionService().findByTestId(testId).size();

        checkTestTime(result, test);

        int numberOfRightAnswers = Objects.nonNull(answers) ? getNumberOfRightAnswers(answers) : 0;

        int mark = getMark(totalNumberOfQuestions, numberOfRightAnswers);

        updateResult(result, mark);

        request.setAttribute(Parameter.MARK, mark);

        return Path.PAGE_MESSAGE;

    }

    private void updateResult(Result result, int mark) {
        result.setResult(mark);
        getResultService().update(result.getId(), result.getResult());
    }

    private int getMark(int totalNumberOfQuestions, int numberOfRightAnswers) {
        return numberOfRightAnswers * 100 / totalNumberOfQuestions;
    }

    private int getNumberOfRightAnswers(String[] answers) {
        String[] value;
        long questionId;
        long answerId;
        int correctAnswers = 0;
        Map<Long, TreeSet<Long>> map = new HashMap<>();
        TreeSet<Long> chosenAnswers = new TreeSet<>();

        for (String answer : answers) {
            value = answer.split(DELIMITER);
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


        for (Map.Entry<Long, TreeSet<Long>> entry : map.entrySet()) {
            TreeSet<Long> correct = getIdOfCorrectAnswers(entry.getKey());

            if (correct.size() == entry.getValue().size()) {
                if (correct.containsAll(entry.getValue())) {
                    correctAnswers++;
                }
            }

        }
        return correctAnswers;
    }

    private void checkTestTime(Result result, Test test) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - result.getStartTime() > test.getDuration()) {
            System.out.println("FAIL");
        }
    }

    private TreeSet<Long> getIdOfCorrectAnswers(long id) throws DBException {
        List<Answer> answers = getAnswerService().findCorrectByQuestionId(id);
        TreeSet<Long> answersId = new TreeSet<>();
        for (Answer answer : answers) {
            answersId.add(answer.getId());
        }
        return answersId;
    }


}
