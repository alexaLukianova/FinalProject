package ua.nure.lukianova.dao;

import ua.nure.lukianova.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDAO extends JdbcAbstractDAO implements UserDAO {

    private static final String SQL__SELECT_ALL_USERS = "SELECT * FROM USERS";

    {
        sqlSelectAll = SQL__SELECT_ALL_USERS;
    }

    @Override
    protected Object createEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("username"),
                resultSet.getBytes("avatar"));
    }
}
