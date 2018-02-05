package ua.nure.lukianova.SummaryTask4.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.web.command.UpdateQuestionInfoCommand;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextListener.class);
    private static final String LOCALES = "locales";

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext context = event.getServletContext();
        String localesFileName = context.getInitParameter(LOCALES);
        String localesFileRealPath = context.getRealPath(localesFileName);
        Properties locales = new Properties();
        try {
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.setAttribute(LOCALES, locales);
        locales.list(System.out);
    }
}
