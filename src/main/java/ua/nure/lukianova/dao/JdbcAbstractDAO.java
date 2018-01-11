package ua.nure.lukianova.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Реализация паттерна Шаблонный метод, Generic

public abstract class JdbcAbstractDAO<T> {
    protected ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    protected static Logger LOGGER = LoggerFactory.getLogger(JdbcTestDAO.class);
    protected String sqlSelectAll;
    protected String sqlInsertInto;

    public List<T> findAll() {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet result;
        List<T> tests = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sqlSelectAll);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                tests.add(createEntity(result));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);

        } finally {
            close(preparedStatement, connection);
        }
        return tests;
    }


    protected void execute(String sql, String... parameters) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            int index = 0;
            for (String parameter : parameters) {
                preparedStatement.setString(++index, parameter);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(preparedStatement, connection);
        }
    }


    protected abstract T createEntity(ResultSet resultSet) throws SQLException;

    private static void close(Statement statement, Connection connection) {
        try {
            if (Objects.nonNull(statement)) {
                statement.close();
            }
            if (Objects.nonNull(connection)) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
