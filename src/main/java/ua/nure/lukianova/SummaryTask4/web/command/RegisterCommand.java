package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.bean.UserValidatorBean;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.AddUserValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;

public class RegisterCommand extends Command {

    private static final long serialVersionUID = 8266438385891590070L;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCommand.class);
    private Validator validator;
    private Map<String, String> errors;

    public RegisterCommand() {
        this.validator = new AddUserValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        UserValidatorBean userValidatorBean = extractUserValidationBean(request);

        errors = validator.validate(userValidatorBean);

        if (!errors.isEmpty()) {
            setUserInfoBack(request, userValidatorBean);
            return Path.PAGE_REGISTRATION;
        }

        getUserService().create(userValidatorBean);
        User user = getUserService().findByLogin(userValidatorBean.getUsername());


        HttpSession session = request.getSession();

        if (Objects.nonNull(session.getAttribute(USER_ROLE))) {
            return Path.COMMAND_LIST_USERS;
        }

        setUserInSessionScope(user, session);
        return Path.COMMAND_SHOW_PROFILE;
    }

    private void setUserInSessionScope(User user, HttpSession session) {
        Role userRole = Role.getRole(user);
        session.setAttribute(USER, user);
        LOGGER.trace("Set the session attribute: user --> " + user);
        session.setAttribute(USER_ROLE, userRole);
        LOGGER.trace("Set the session attribute: userRole --> " + userRole);
        LOGGER.info("User " + user + " logged as " + userRole.toString().toLowerCase());
        LOGGER.debug("Command finished");
    }

    private void setUserInfoBack(HttpServletRequest request, UserValidatorBean userValidatorBean) {
        request.setAttribute(ERRORS, errors);
        request.setAttribute(USERNAME, userValidatorBean.getUsername());
        request.setAttribute(FIRST_NAME, userValidatorBean.getFirstName());
        request.setAttribute(LAST_NAME, userValidatorBean.getLastName());
        request.setAttribute(PASSWORD, userValidatorBean.getPassword());
        request.setAttribute(REENTER_PASSWORD, userValidatorBean.getReenterPassword());
    }

    private UserValidatorBean extractUserValidationBean(HttpServletRequest request) {
        UserValidatorBean userValidatorBean = new UserValidatorBean();
        userValidatorBean.setFirstName(request.getParameter(FIRST_NAME).trim());
        userValidatorBean.setLastName(request.getParameter(LAST_NAME).trim());
        userValidatorBean.setUsername(request.getParameter(USERNAME).trim());
        userValidatorBean.setPassword(request.getParameter(PASSWORD).trim());
        userValidatorBean.setReenterPassword(request.getParameter(REENTER_PASSWORD).trim());
        userValidatorBean.setRoleId(Integer.valueOf(request.getParameter(USER_ROLE)));
        return userValidatorBean;
    }
}
