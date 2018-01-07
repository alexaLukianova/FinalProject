package ua.nure.lukianova.dao;

import ua.nure.lukianova.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDAO implements UserDAO {

    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static Logger LOGGER = LoggerFactory.getLogger(JdbcUserDAO.class);

    private static final String SQL__SELECT_ALL_USERS = "SELECT * FROM USERS";

    @Override
    public List<User> findAll() {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet result;
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL__SELECT_ALL_USERS);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                users.add(createUser(result));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);

        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
        return users;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("username"),
                resultSet.getBytes("avatar"));
    }

}
