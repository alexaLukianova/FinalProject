package ua.nure.lukianova.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.entity.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcAnswerDAO implements AnswerDAO{

//    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
//    private static Logger LOGGER = LoggerFactory.getLogger(JdbcUserDAO.class);
//
//    private static final String SQL__SELECT_ALL_USERS = "SELECT * FROM USERS";
//
//    @Override
//    public boolean create(Answer answer) {answer.getText());
//        return false;
//    }
//
//    private void execute(String sql, String... parameters) {
//        Connection connection = connectionFactory.getConnection();
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            int index = 0;
//            for (String parameter : parameters) {
//                preparedStatement.setString(++index, parameter);
//            }
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            close(preparedStatement, connection);
//        }
//    }
//
//    private void close(Statement statement, Connection connection) {
//        try {
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
