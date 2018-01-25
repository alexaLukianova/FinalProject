package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.bean.TestResultBean;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ShowProfileCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (Role.getRole(user) == Role.STUDENT) {
            List<TestResultBean> userProgress = getResultService().findResultByUserId(user.getId());
            request.setAttribute("userProgress", userProgress);
        }


        return Path.PAGE_PROFILE;
    }
}
