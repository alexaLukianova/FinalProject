package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.exception.DBException;
import ua.nure.lukianova.SummaryTask4.service.*;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EditTestCommand extends Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException, DBException {

        HttpSession session = request.getSession();
        if (Objects.nonNull(request.getParameter("test_id"))) {
//            long testId = Long.valueOf((session.getAttribute("edit_test_id")).toString());
//
//            session.setAttribute("edit_test_id", request.getParameter("edit_test_id"));

            long testId = Long.valueOf(request.getParameter("test_id"));

            Test test = getTestService().findTestById(testId);

            request.setAttribute("test", test);

//            request.setAttribute("test_subject", test.getSubject());
//            request.setAttribute("test_complexity", test.getComplexity());
//            request.setAttribute("test_time", test.getTime());


//            List<Question> questionList = questionService.findByTestId(testId);
//
//            HashMap<Question, List<Answer>> testInfo = new HashMap<>();
//            for (Question question : questionList) {
//                testInfo.put(question, answerService.findByQuestionId(question.getId()));
//            }

List<Question> questions = getQuestionService().findByTestId(testId);

            Map<Question, List<Answer>> questionsAndAnswers = questions.stream()
                    .collect(Collectors.toMap(Function.identity(),
                            question -> getAnswerService().findByQuestionId(question.getId())));


            request.setAttribute("questions", questions);
            request.setAttribute("questionsAndAnswers", questionsAndAnswers);
        }


        return Path.PAGE_EDIT_TEST;
    }
}
