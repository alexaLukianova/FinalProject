package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.exception.DBException;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;

public class UpdateQuestionInfoCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateQuestionInfoCommand.class);
    private static final long serialVersionUID = 5548030734944296696L;
    private Validator answerValidator;
    private Validator questionValidator;
    private Map<String, String> errors;
    private long testId;
    private QuestionService questionService;
    private AnswerService answerService;

    public UpdateQuestionInfoCommand(Validator answerValidator, Validator questionValidator,
                                     QuestionService questionService, AnswerService answerService) {
        this.answerValidator = answerValidator;
        this.questionValidator = questionValidator;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        errors = new LinkedHashMap<>();
        if(StringUtils.isEmpty(request.getParameter(TEST_ID))){
            throw new AppException("Invalid input");
        }
        testId = Long.valueOf(request.getParameter(TEST_ID));

        Question question = extractQuestion(request);
        List<Answer> answers = extractAnswers(request);

        errors.putAll(questionValidator.validate(question));
        errors.putAll(answerValidator.validate(answers));

        if (MapUtils.isEmpty(errors)) {
            updateQuestionInfo(question, answers);
        } else {
            LOGGER.trace("Found errors: --> " + errors);
            setAttributesIntoSessionScope(request);
        }

        LOGGER.debug("Command finished");

        return getURL();
    }

    private void setAttributesIntoSessionScope(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(ERRORS, new HashMap<>(errors));
        LOGGER.trace("Set the request attribute: errors --> " + errors);

        session.setAttribute(QUESTION_ID, request.getParameter(QUESTION_ID));
        LOGGER.trace("Set the request attribute: question id --> " + request.getParameter(QUESTION_ID));
    }

    private String getURL() {
        return new StringBuilder(Path.COMMAND_SHOW_EDIT_FORM)
                .append(DELIMITER)
                .append(TEST_ID).append(EQUAL_SIGN).append(testId)
                .toString();
    }

    private Question extractQuestion(HttpServletRequest request) {
        Question question = new Question();
        question.setTestId(Long.valueOf(request.getParameter(TEST_ID)));
        question.setId(Long.valueOf(request.getParameter(QUESTION_ID)));
        question.setText(request.getParameter(QUESTION));
        return question;
    }

    private List<Answer> extractAnswers(HttpServletRequest request) {
        List<Answer> answers = new ArrayList<>();
        String[] ids = request.getParameterValues(ANSWER_ID);
        String[] texts = request.getParameterValues(ANSWER);
        String[] corrects = request.getParameterValues(ANSWER_CORRECT);

        List<String> correctAnswers = ArrayUtils.isNotEmpty(corrects)
                ? Arrays.asList(corrects)
                : Collections.emptyList();

        for (int i = 0; i < ids.length; i++) {
            Answer answer = new Answer();
            answer.setId(Long.valueOf(ids[i]));
            answer.setText(texts[i]);
            answer.setCorrect(correctAnswers.contains(ids[i]));
            answers.add(answer);
        }

        return answers;
    }

    private void updateQuestionInfo(Question question, List<Answer> answers) throws DBException {
        questionService.update(question);
        answerService.updateAll(answers);
    }

}
