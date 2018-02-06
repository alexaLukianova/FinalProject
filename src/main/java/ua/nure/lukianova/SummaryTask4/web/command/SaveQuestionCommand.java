package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SaveQuestionCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveQuestionCommand.class);
    private static final long serialVersionUID = 6397119223393351766L;
    private Validator answerValidator;
    private Validator questionValidator;
    private Map<String, String> errors;
    private long testId;
    private QuestionService questionService;
    private AnswerService answerService;

    public SaveQuestionCommand(Validator answerValidator, Validator questionValidator,
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

        if (StringUtils.isEmpty(request.getParameter(Parameter.TEST_ID))) {
            throw new AppException("Invalid input");
        }

        testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));

        if (testId <= 0) {
            throw new AppException("Invalid input");
        }

        Question question = extractNewQuestion(request);
        List<Answer> answers = extractNewAnswers(request);

        if (isValidQuestionInfo(question, answers)) {
            setQuestionInfo(question, answers);
            return getURL(Path.COMMAND_SHOW_EDIT_FORM);
        }
        LOGGER.trace("Found errors: --> " + errors);
        setAttributesIntoSessionScope(request, question.getText());

        LOGGER.debug("Command finished");
        return getURL(Path.COMMAND_SHOW_SAVE_QUESTION_FORM);
    }

    private void setAttributesIntoSessionScope(HttpServletRequest request, String question) {
        HttpSession session = request.getSession();
        session.setAttribute(Parameter.ERRORS, new HashMap<>(errors));
        LOGGER.trace("Set the session attribute: errors --> " + errors);

        session.setAttribute(Parameter.QUESTION, question);
        LOGGER.trace("Set the session attribute: question --> " + question);
    }

    private String getURL(String path) {
        return new StringBuilder(path).append(DELIMITER).append(Parameter.TEST_ID).append(EQUAL_SIGN).append(testId).toString();
    }

    private Question extractNewQuestion(HttpServletRequest request) {
        Question question = new Question();
        question.setText(request.getParameter(Parameter.QUESTION));
        question.setTestId(Long.valueOf(request.getParameter(Parameter.TEST_ID)));
        return question;
    }

    private List<Answer> extractNewAnswers(HttpServletRequest request) {
        List<Answer> answers = new ArrayList<>();
        String[] text = request.getParameterValues(Parameter.ANSWER);
        String[] correct = request.getParameterValues(Parameter.ANSWER_CORRECT);
        List<String> correctAnswers = new ArrayList<>();
        if (Objects.nonNull(correct)) {
            correctAnswers = new ArrayList<>(Arrays.asList(correct));
        }
        for (int i = 0; i < text.length; i++) {
            Answer answer = new Answer();
            answer.setText(text[i]);
            answer.setCorrect(correctAnswers.contains(String.valueOf(i + 1)));
            answers.add(answer);
        }

        return answers;
    }

    private boolean isValidQuestionInfo(Question question, List<Answer> answers) {
        Map<String, String> questionErrors = new LinkedHashMap<>();
        questionErrors.putAll(questionValidator.validate(question));
        questionErrors.putAll(answerValidator.validate(answers));
        errors.putAll(questionErrors);
        return questionErrors.isEmpty();
    }

    private void setQuestionInfo(Question question, List<Answer> answers) {
        long questionId = questionService.create(question);
        answers.forEach(answer -> answer.setQuestionId(questionId));
        answerService.createAll(answers);
    }


}

