package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.QUESTION_ID;
import static ua.nure.lukianova.SummaryTask4.web.Parameter.TEST_ID;

public class DeleteQuestionCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteQuestionCommand.class);
    private static final long serialVersionUID = 1705333156295756631L;
    private QuestionService questionService;
    private static final int MIN_QUESTION_COUNT = 0;
    private long testId;

    public DeleteQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        long questionId = Long.valueOf(request.getParameter(QUESTION_ID));
        testId = Long.valueOf(request.getParameter(TEST_ID));

        if (questionService.findByTestId(testId).size() > MIN_QUESTION_COUNT) {
            questionService.delete(questionId);
        }

        return getURL();
    }

    private String getURL() {
        return new StringBuilder(Path.COMMAND_SHOW_EDIT_FORM).append(DELIMITER)
                .append(TEST_ID).append(EQUAL_SIGN).append(testId)
                .toString();
    }
}
