package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDAO extends JdbcAbstractDAO<User> implements UserDAO {

    private static final String SQL__SELECT_ALL_USERS = "SELECT * FROM USERS";
    private static final String SQL__SELECT_USER_BY_LOGIN = "SELECT * FROM USERS WHERE USERNAME=?";

    {
        sqlSelectAll = SQL__SELECT_ALL_USERS;
    }

    @Override
    protected User extractEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(Fields.ENTITY_ID));
        user.setUsername(resultSet.getString(Fields.USER_USERNAME));
        user.setPassword(resultSet.getString(Fields.USER_PASSWORD));
        user.setFirstName(resultSet.getString(Fields.USER_FIRST_NAME));
        user.setLastName(resultSet.getString(Fields.USER_LAST_NAME));
        user.setRoleId(resultSet.getInt(Fields.USER_ROLE_ID));
        return user;
    }

    @Override
    public User findUserByLogin(String username) throws DBException{
        return findBy(SQL__SELECT_USER_BY_LOGIN, username).get(0);
    }
}
