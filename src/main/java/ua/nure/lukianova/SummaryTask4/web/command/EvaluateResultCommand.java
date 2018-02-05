package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
import ua.nure.lukianova.SummaryTask4.web.Parameter;

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
        LOGGER.debug("Command starts");

        if (StringUtils.isEmpty(request.getParameter(Parameter.TEST_ID))
                || StringUtils.isEmpty(request.getParameter(Parameter.RESULT_ID))
                || Objects.isNull(request.getParameterValues(Parameter.ANSWER_CORRECT))) {
            throw new AppException("Invalid input");
        }

        long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
        long resultId = Long.valueOf(request.getParameter(Parameter.RESULT_ID));
        String[] answers = request.getParameterValues(Parameter.ANSWER_CORRECT);


        Result result = resultService.findById(resultId);
        LOGGER.trace("Found in DB: result --> " + result);

        Test test = testService.findById(testId);
        LOGGER.trace("Found in DB: test --> " + test);

        int totalNumberOfQuestions = questionService.findByTestId(testId).size();
        LOGGER.trace("Found in DB: questions count --> " + totalNumberOfQuestions);

        int numberOfRightAnswers = ArrayUtils.isNotEmpty(answers) ? getNumberOfRightAnswers(answers) : 0;

        int mark = getMark(totalNumberOfQuestions, numberOfRightAnswers);

        updateResult(result, mark);

        setMarkIntoSessionScope(request, mark);

        LOGGER.debug("Command finished");
        return Path.COMMAND_SHOW_RESULT_FORM;

    }

    private void setMarkIntoSessionScope(HttpServletRequest request, int mark) {
        HttpSession session = request.getSession();
        session.setAttribute(Parameter.MARK, mark);
        LOGGER.trace("Set the session attribute: mark --> " + mark);
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

    private TreeSet<Long> getIdOfCorrectAnswers(long id) throws DBException {
        List<Answer> answers = answerService.findCorrectByQuestionId(id);
        LOGGER.trace("Found in DB: correct answers --> " + answers);

        TreeSet<Long> answersId = new TreeSet<>();
        for (Answer answer : answers) {
            answersId.add(answer.getId());
        }
        return answersId;
    }

}
