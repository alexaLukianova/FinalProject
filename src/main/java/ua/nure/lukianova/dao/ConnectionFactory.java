package ua.nure.lukianova.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static final String URL_KEY = "url";
    private static final String USER_KEY = "user";
    private static final String PASSWORD_KEY = "password";
    private static final String DRIVER_KEY = "driver";
    private static final String CONFIG = "config";

    private String url;
    private String driver;
    private String user;
    private String password;

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {
        if (isNullDbConnectionData()) {
            loadConnectionDbData();
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadConnectionDbData() {
        ResourceBundle resource = ResourceBundle.getBundle(CONFIG);
        user = resource.getString(USER_KEY);
        url = resource.getString(URL_KEY);
        driver = resource.getString(DRIVER_KEY);
        password = resource.getString(PASSWORD_KEY);
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    private boolean isNullDbConnectionData() {
        return Objects.isNull(user) || Objects.isNull(password)
                || Objects.isNull(url) || Objects.isNull(driver);
    }
}
