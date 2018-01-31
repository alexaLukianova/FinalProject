package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.bean.UserValidatorBean;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;

public class ShowRegisterCommand extends Command {
    private static final long serialVersionUID = -4704807513625034456L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        if (Objects.nonNull(session.getAttribute(ERRORS))) {
            Map<String, String> errors = (Map<String, String>) session.getAttribute(ERRORS);
            UserValidatorBean user = (UserValidatorBean) session.getAttribute(USER);

            setAttributes(request, errors, user);

            session.removeAttribute(ERRORS);
            session.removeAttribute(USER);
        }

        return Path.PAGE_REGISTRATION;

    }

    private void setAttributes(HttpServletRequest request, Map<String, String> errors, UserValidatorBean user) {
        request.setAttribute(FIRST_NAME, user.getFirstName());
        request.setAttribute(LAST_NAME, user.getLastName());
        request.setAttribute(USERNAME, user.getUsername());
        request.setAttribute(PASSWORD, user.getPassword());
        request.setAttribute(REENTER_PASSWORD, user.getReenterPassword());
        request.setAttribute(USER_ROLE, user.getRoleId());
        request.setAttribute(ERRORS, errors);
    }


}
