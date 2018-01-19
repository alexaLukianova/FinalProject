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
    private DataSource ds;

    public static synchronized ConnectionFactory getInstance() {
        try {
            if (instance == null) {
                instance = new ConnectionFactory();
            }
            return instance;
        } catch (DBException e) {
            throw new RuntimeException(e.getMessage(), e);
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

}
