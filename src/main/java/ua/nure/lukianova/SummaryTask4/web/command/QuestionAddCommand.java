//package ua.nure.lukianova.SummaryTask4.web.command;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import ua.nure.lukianova.SummaryTask4.db.entity.Test;
//import ua.nure.lukianova.SummaryTask4.exception.AppException;
//import ua.nure.lukianova.SummaryTask4.service.TestService;
//import ua.nure.lukianova.SummaryTask4.service.TestServiceImpl;
//import ua.nure.lukianova.SummaryTask4.web.Path;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//public class QuestionAddCommand extends Command {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(CommandContainer.class);
//    private static final long serialVersionUID = -7937567362584001987L;
//
//    private static TestService testService;
//
//    static
//    {
//        testService = new TestServiceImpl();
//    }
//
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
//       request.setCharacterEncoding("UTF-8");
//
//        LOGGER.debug("Command starts");
//        HttpSession session = request.getSession();
//        session.setAttribute("questionsNumber", request.getParameter("test_questionQuantity"));
//        session.setAttribute("answersNumber", request.getParameter("test_answerQuantity"));
//
//        Test test = extract(request, response);
//        long newTestId = testService.create(test);
//
//        session.setAttribute("testId", newTestId);
//        return Path.PAGE_ADD_QUESTIONS;
//    }
//
//
//    private Test extract( HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//        Test test = new Test();
//        test.setName(request.getParameter("test_name"));
//        test.setSubject(request.getParameter("test_subject"));
//        test.setComplexityId(Integer.valueOf(request.getParameter("test_complexityId")));
//        test.setDuration(Long.valueOf(request.getParameter("test_time")));
//        return test;
//    }
//
//
//}
//
