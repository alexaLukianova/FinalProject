package ua.nure.lukianova.servlet;

import ua.nure.lukianova.entity.Test;
import ua.nure.lukianova.service.TestService;
import ua.nure.lukianova.service.TestServiceImpl;

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
        req.getRequestDispatcher("/WEB-INF/view/test_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        testService.create(getEntity(req));
        Cookie cookie = new Cookie("questionQuantity", req.getParameter("new_test_quantity_of_questions"));
        Cookie cookie1 = new Cookie("questionAnswers", req.getParameter("new_test_quantity_of_answers"));
        resp.addCookie(cookie);
        resp.addCookie(cookie1);
        resp.sendRedirect("/question_add");
    }

    private Test getEntity(HttpServletRequest request) {
        return new Test(request.getParameter("new_test_name"),
                request.getParameter("new_test_subject"),
                request.getParameter("new_test_complexity"));

    }
}
