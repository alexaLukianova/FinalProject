package ua.nure.lukianova.SummaryTask4.exception;

public class DBException extends AppException {

    private static final long serialVersionUID = 4969592588956136601L;

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
