package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {


    protected static final int ANSWERS_COUNT = 4;
    protected static final String DELIMITER = "&";
    protected static final String EQUAL_SIGN = "=";

    public Command() {
    }

    private static final long serialVersionUID = 520974220615423152L;

    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}



