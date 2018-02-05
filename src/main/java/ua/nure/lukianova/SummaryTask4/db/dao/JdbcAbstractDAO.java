package ua.nure.lukianova.SummaryTask4.db.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.exception.DBException;
import ua.nure.lukianova.SummaryTask4.exception.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Реализация паттерна Шаблонный метод, Generic

public abstract class JdbcAbstractDAO<T> {
    protected ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    protected static Logger LOGGER = LoggerFactory.getLogger(JdbcTestDAO.class);
    protected String sqlSelectAll;
    protected String sqlSelectById;

    public List<T> findAll() throws DBException {
        return findBy(sqlSelectAll);
    }

    protected long execute(String sql, String... parameters) throws DBException {
        long id = -1;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int index = 0;
            for (String parameter : parameters) {
                preparedStatement.setString(++index, parameter);
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys(); //return if only auto-generated key
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error(Message.ERR_CANNOT_OBTAIN_QUERY, e);
            throw new DBException(Message.ERR_CANNOT_OBTAIN_QUERY, e);
        }

        return id;
    }


    protected List<T> findBy(String sql, String... parameters) throws DBException {
        List<T> entities = new ArrayList<>();
        ResultSet result;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int index = 0;
            for (String parameter : parameters) {
                preparedStatement.setString(++index, parameter);
            }
            result = preparedStatement.executeQuery();
            while (result.next()) {
                entities.add(extractEntity(result));
            }
        } catch (SQLException e) {
            LOGGER.error(Message.ERR_CANNOT_FIND_ENTITIES, e);
            throw new DBException(Message.ERR_CANNOT_FIND_ENTITIES, e);
        }
        return entities;
    }

    public T findById(long id) throws DBException {
        List<T> question = findBy(sqlSelectById, String.valueOf(id));
        return question.isEmpty() ? null : question.get(0);
    }

    protected abstract T extractEntity(ResultSet resultSet) throws SQLException;

}
