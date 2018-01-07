package ua.nure.lukianova.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    private JdbcUtils(){}

    public static void close(Statement statement, Connection connection) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
