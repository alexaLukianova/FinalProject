//package ua.nure.lukianova.SummaryTask4.web.command;
//
//import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
//import ua.nure.lukianova.SummaryTask4.db.entity.Question;
//import ua.nure.lukianova.SummaryTask4.db.entity.Test;
//import ua.nure.lukianova.SummaryTask4.exception.AppException;
//import ua.nure.lukianova.SummaryTask4.web.Path;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.*;
//
//public class ShowAddQuestionForm extends Command {
//    private static final long serialVersionUID = -878132076893965513L;
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
//
//        request.setAttribute("testId", request.getParameter("testId"));
//        request.setAttribute("answers_number",request.getParameter("answers_number") );
//
//        return Path.PAGE_ADD_NEW_QUESTION;
//    }
//}
