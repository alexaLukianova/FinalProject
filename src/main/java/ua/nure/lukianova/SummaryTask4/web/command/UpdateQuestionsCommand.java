package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateQuestionsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        long questionId = Long.valueOf(request.getParameter("question_id"));

        request.setAttribute("test_id", request.getParameter("test_id"));

        getQuestionService().update(questionId, request.getParameter("question"));

        List<Answer> answers = getAnswerService().findByQuestionId(questionId);

        int i = 0;
        for (Answer answer : answers) {
            boolean correct = "is_correct".equals(request.getParameter("correct" + i));
            getAnswerService().update(answer.getId(), request.getParameter("answer" + i), correct);
            i++;
        }

        return Path.COMMAND_EDIT_TEST;
    }
}
