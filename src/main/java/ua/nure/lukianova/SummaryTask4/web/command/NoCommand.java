package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoCommand.class);
    private static final long serialVersionUID = -719778168133462730L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Command starts");

        String errorMessage = "No such command";

        request.setAttribute(Parameter.ERROR_MESSAGE, errorMessage);
        LOGGER.error("Set the request attribute: errorMessage --> " + errorMessage);

        LOGGER.debug("Command finished");
        return Path.PAGE_ERROR_PAGE;
    }
}
