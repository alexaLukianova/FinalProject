package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.bean.UserValidatorBean;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ShowRegisterCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowRegisterCommand.class);
    private static final long serialVersionUID = -4704807513625034456L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession session = request.getSession();
        if (Objects.nonNull(session.getAttribute(Parameter.ERRORS))) {
            Map<String, String> errors = (Map<String, String>) session.getAttribute(Parameter.ERRORS);
            UserValidatorBean user = (UserValidatorBean) session.getAttribute(Parameter.USER);
            setAttributes(request, errors, user);
            session.removeAttribute(Parameter.ERRORS);
            session.removeAttribute(Parameter.USER);
        }

        return Path.PAGE_REGISTRATION;
    }

    private void setAttributes(HttpServletRequest request, Map<String, String> errors, UserValidatorBean user) {
        request.setAttribute(Parameter.FIRST_NAME, user.getFirstName());
        request.setAttribute(Parameter.LAST_NAME, user.getLastName());
        request.setAttribute(Parameter.USERNAME, user.getUsername());
        request.setAttribute(Parameter.PASSWORD, user.getPassword());
        request.setAttribute(Parameter.REENTER_PASSWORD, user.getReenterPassword());
        request.setAttribute(Parameter.USER_ROLE, user.getRoleId());
        request.setAttribute(Parameter.ERRORS, errors);
    }

}
