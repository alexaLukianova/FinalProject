package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.bean.UserValidatorBean;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;
import ua.nure.lukianova.SummaryTask4.web.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;


public class RegisterCommand extends Command {

    private static final long serialVersionUID = 8266438385891590070L;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCommand.class);
    private Validator userValidator;
    private Map<String, String> errors;
    private UserService userService;

    public RegisterCommand(Validator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        UserValidatorBean userValidatorBean = extractUserValidationBean(request);
        errors = userValidator.validate(userValidatorBean);

        if (MapUtils.isNotEmpty(errors)) {
            setUserInfoBack(request, userValidatorBean);
            return Path.COMMAND_SHOW_REGISTER_FORM;
        }

        userService.create(userValidatorBean);
        User user = userService.findByLogin(userValidatorBean.getUsername());

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
        HttpSession session = request.getSession();
        session.setAttribute(Parameter.USER, userValidatorBean);
        session.setAttribute(Parameter.ERRORS, errors);
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
