package ua.nure.lukianova.SummaryTask4.db.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.exception.DBException;
import ua.nure.lukianova.SummaryTask4.exception.Message;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);

    private static ConnectionFactory instance;

    public static synchronized ConnectionFactory getInstance() {
        try{
            if (instance == null) {
                instance = new ConnectionFactory();
            }
            return instance;
        }catch (DBException e){
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    private ConnectionFactory() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/ST4DB");
            LOGGER.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOGGER.error(Message.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Message.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    private DataSource ds;












//    private static final String URL_KEY = "url";
//    private static final String USER_KEY = "user";
//    private static final String PASSWORD_KEY = "password";
//    private static final String DRIVER_KEY = "driver";
//    private static final String CONFIG = "config";
//
//    private String url;
//    private String driver;
//    private String user;
//    private String password;
//
//    private static ConnectionFactory connectionFactory = null;

//    private ConnectionFactory() {
//        if (isNullDbConnectionData()) {
//            loadConnectionDbData();
//        }
//        try {
//            Class.forName(driver);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


//    public Connection getConnection() {
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }


    public Connection getConnection() throws DBException {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOGGER.error(Message.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Message.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

//    private void loadConnectionDbData() {
//        ResourceBundle resource = ResourceBundle.getBundle(CONFIG);
//        user = resource.getString(USER_KEY);
//        url = resource.getString(URL_KEY);
//        driver = resource.getString(DRIVER_KEY);
//        password = resource.getString(PASSWORD_KEY);
//    }
//
//    public static ConnectionFactory getInstance() {
//        if (connectionFactory == null) {
//            connectionFactory = new ConnectionFactory();
//        }
//        return connectionFactory;
//    }
//
//    private boolean isNullDbConnectionData() {
//        return Objects.isNull(user) || Objects.isNull(password)
//                || Objects.isNull(url) || Objects.isNull(driver);
//    }
}
