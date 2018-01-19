package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteQuestionCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession session = request.getSession();

        String questionText = request.getParameter("delete_question_text");

        // при сохранении теста удалить полностью тест и заново вставить или сейчас удалить из таблицы тест?

        // comparator

        Question question = new Question();

        Map<Question, List<Answer>> testInfo = (HashMap<Question, List<Answer>>) session.getAttribute("testInfo");
        List<Question> questionList = (ArrayList<Question>) session.getAttribute("questionList");



        session.setAttribute("questionList", questionList);
        session.setAttribute("testInfo", testInfo);

        return Path.COMMAND_EDIT_TEST;
    }
}
