package ua.nure.lukianova.SummaryTask4.servlet;

import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.service.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/test_add"})
public class TestAddServlet extends HttpServlet {
    private TestService testService;

    @Override
    public void init() throws ServletException {
        super.init();
        testService = new TestServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/testAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println(testService.create(getEntity(req)));
        Cookie cookie = new Cookie("questionQuantity", req.getParameter("new_test_quantity_of_questions"));
        Cookie cookie1 = new Cookie("questionAnswers", req.getParameter("new_test_quantity_of_answers"));
        resp.addCookie(cookie);
        resp.addCookie(cookie1);
        resp.sendRedirect("/question_add");
    }

    private Test getEntity(HttpServletRequest request) {
        return null;
//        new Test(request.getParameter("new_test_name"),
//                request.getParameter("new_test_subject"),
//                request.getParameter("new_test_complexity"));

    }
}
