package ua.nure.lukianova.servlet;

import ua.nure.lukianova.service.TestService;
import ua.nure.lukianova.service.TestServiceImpl;
import ua.nure.lukianova.service.UserService;
import ua.nure.lukianova.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {
    private TestService testService;

    @Override
    public void init() throws ServletException {
        super.init();
        testService = new TestServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tests", testService.findAllTests());
        req.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(req, resp);
    }
}
