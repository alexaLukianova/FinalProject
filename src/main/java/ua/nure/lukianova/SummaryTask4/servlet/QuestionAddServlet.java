//package ua.nure.lukianova.SummaryTask4.servlet;
//
//import ua.nure.lukianova.SummaryTask4.db.dao.JdbcQuestionDAO;
//import ua.nure.lukianova.SummaryTask4.db.entity.Question;
//import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet(urlPatterns = {"/question_add"})
//public class QuestionAddServlet extends HttpServlet {
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//        Cookie[] cookies = req.getCookies();
//        String quest = null, answ = null;
//
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("questionQuantity")) {
//                    quest = cookie.getValue();
//                    System.out.println(quest);
//                }
//                if (cookie.getName().equals("questionAnswers")) {
//                    answ = cookie.getValue();
//                    System.out.println(answ);
//                }
//            }
//        }
//
//        req.setAttribute("q", Integer.valueOf(quest));
//        req.setAttribute("a",Integer.valueOf(answ));
//
//        req.getRequestDispatcher("/WEB-INF/view/questionAdd.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//        Question question;
//        List<Answer> answers = new ArrayList<>();
//        for (int i = 1; i < 5; i++) {
//            String ans = req.getParameter("new_answer" + i);
//            boolean correct = "is_correct".equals(req.getParameter("correct" + i));
//            answers.add(new Answer(ans, correct));
//        }
//        question = new Question(req.getParameter("new_question"), answers);
//        JdbcQuestionDAO jdbcQuestionDAO = new JdbcQuestionDAO();
//        jdbcQuestionDAO.create(question);
//
//        req.getRequestDispatcher("/WEB-INF/view/question.jsp").forward(req, resp);
//    }
//
////    private Question getEntity(HttpServletRequest request) {
////        return new Question(request.getParameter("new_test_name"),
////                request.getParameter("new_test_subject"),
////                request.getParameter("new_test_complexity"));
////
////    }
//
////    private Answer getEntity(HttpServletRequest request) {
////        return new Answer(request.getParameter("new_test_name"),
////                request.getParameter("new_test_subject"),
////                request.getParameter("new_test_complexity"));
//
//   // }
//
//
//}
