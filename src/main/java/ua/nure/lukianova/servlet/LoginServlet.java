package ua.nure.lukianova.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO implement url select
        req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.nonNull(req.getUserPrincipal())) {
            req.getSession().invalidate();
        }
        try {
            req.login(req.getParameter("j_username"), req.getParameter("j_password"));
            //String userRole = req.isUserInRole("ADMIN") ? "admin" : "student";
            // TODO implement redirect to servlets
            //  req.getRequestDispatcher("/WEB-INF/view/" + userRole + "Landing.jsp").forward(req, resp);
            resp.sendRedirect("/admin/landing");
        } catch (Exception e) {
            req.getRequestDispatcher("/WEB-INF/view/fail_login.jsp").forward(req, resp);
        }

    }
}
