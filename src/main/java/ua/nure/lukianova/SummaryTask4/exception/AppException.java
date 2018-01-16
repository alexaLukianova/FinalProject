package ua.nure.lukianova.SummaryTask4.exception;

public class AppException extends Exception{

    private static final long serialVersionUID = -1342193952982597709L;

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
