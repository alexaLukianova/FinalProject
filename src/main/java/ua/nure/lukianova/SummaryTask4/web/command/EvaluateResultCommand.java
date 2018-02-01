package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.exception.DBException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;

public class EvaluateResultCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateResultCommand.class);
    private static final long serialVersionUID = 8428428763878912055L;
    private ResultService resultService;
    private TestService testService;
    private QuestionService questionService;
    private AnswerService answerService;

    public EvaluateResultCommand(ResultService resultService, TestService testService, QuestionService questionService,
                                 AnswerService answerService) {
        this.resultService = resultService;
        this.testService = testService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {


        long testId = Long.valueOf(request.getParameter(TEST_ID));
        long resultId = Long.valueOf(request.getParameter(RESULT_ID));

        String[] answers = request.getParameterValues(ANSWER_CORRECT);
        Result result = resultService.findById(resultId);
        Test test = testService.findById(testId);


        int totalNumberOfQuestions = questionService.findByTestId(testId).size();

        checkTestTime(result, test); //TODO maybe it is not good idea

        int numberOfRightAnswers = Objects.nonNull(answers) ? getNumberOfRightAnswers(answers) : 0;

        int mark = getMark(totalNumberOfQuestions, numberOfRightAnswers);

        updateResult(result, mark);

        setMarkIntoSessionScope(request, mark);

        return Path.COMMAND_SHOW_RESULT_FORM;

    }

    private void setMarkIntoSessionScope(HttpServletRequest request, int mark) {
        HttpSession session = request.getSession();
        session.setAttribute(MARK, mark);
    }

    private void updateResult(Result result, int mark) {
        result.setResult(mark);
        resultService.update(result.getId(), result.getResult());
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
        List<Answer> answers = answerService.findCorrectByQuestionId(id);
        TreeSet<Long> answersId = new TreeSet<>();
        for (Answer answer : answers) {
            answersId.add(answer.getId());
        }
        return answersId;
    }

}
