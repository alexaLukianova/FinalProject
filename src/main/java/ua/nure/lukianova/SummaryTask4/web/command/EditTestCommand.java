package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.*;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EditTestCommand extends Command {

    private TestService testService;
    private QuestionService questionService;
    private AnswerService answerService;

    {
        testService = new TestServiceImpl();
        questionService = new QuestionServiceImpl();
        answerService = new AnswerServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
       long testId = Long.valueOf(request.getParameter("edit_id"));
       Test test =  testService.findTestById(testId);
       request.setAttribute("test_name", test.getName());
       request.setAttribute("test_subject", test.getSubject());
       request.setAttribute("test_complexity", test.getComplexity());
       request.setAttribute("test_time", test.getTime());

       List<Question> questionList = questionService.findQuestionsByTestId(testId);

        HashMap<Question, List<Answer>> testInfo = new HashMap<>();
        for( Question question: questionList){
            testInfo.put(question, answerService.findAnswersByQuestionId(question.getId()));
        }

        // TODO on streams

       request.setAttribute("questionList", questionList);
        request.setAttribute("testInfo", testInfo);

        return Path.PAGE_EDIT_TEST;
    }
}
