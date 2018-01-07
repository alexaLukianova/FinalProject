package ua.nure.lukianova.servlet;

import ua.nure.lukianova.entity.Test;
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

@WebServlet(urlPatterns = {"/admin/landing"}, name = "adminServlet")
public class AdminServlet extends HttpServlet {
    private UserService userService;
    private TestService testService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserServiceImpl();
        testService = new TestServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.findAll());
        req.setAttribute("tests", testService.findAllTests());
        req.getRequestDispatcher("/WEB-INF/view/adminLanding.jsp").forward(req, resp);
    }
}
