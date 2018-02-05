package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.MARK;

public class ShowResultCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowResultCommand.class);
    private static final long serialVersionUID = 2992095752648799140L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        HttpSession session = request.getSession();
        if (Objects.nonNull(session.getAttribute(MARK))) {
            int mark = (Integer) (session.getAttribute(MARK));
            request.setAttribute(MARK, mark);
            LOGGER.trace("Set the request attribute: mark --> " + mark);

            session.removeAttribute(MARK);
        }

        LOGGER.debug("Command finished");
        return Path.PAGE_MESSAGE;
    }
}
