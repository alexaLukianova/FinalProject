package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTestCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteTestCommand.class);
    private static final long serialVersionUID = 1319019089110620927L;
    private TestService testService;


    public DeleteTestCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        if (StringUtils.isEmpty(request.getParameter(Parameter.TEST_ID))) {
            throw new AppException("Invalid input");
        }
        long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
        testService.delete(testId);

        LOGGER.debug("Command finished");
        return Path.COMMAND_LIST_TESTS;
    }

}
