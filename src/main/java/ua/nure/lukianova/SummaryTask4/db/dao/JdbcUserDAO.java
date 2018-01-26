package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcUserDAO extends JdbcAbstractDAO<User> implements UserDAO {

    private static final String SQL__SELECT_ALL_USERS = "SELECT * FROM USERS";
    private static final String SQL__SELECT_USER_BY_LOGIN = "SELECT * FROM USERS WHERE USERNAME=?";
    private static final String SQL__UPDATE_LOCK = "UPDATE USERS SET LOCKED = ? WHERE ID = ?";
    private static final String SQL__INSERT_USER = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, ROLE_ID)" +
            " VALUES(?,?,?,?,?)";
    private static final String SQL__DELETE_USER_BY_LOGIN = "DELETE FROM USERS WHERE ID=(?)";

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
        user.setLocked(resultSet.getBoolean(Fields.USER_LOCKED));
        return user;
    }

    @Override
    public User findByLogin(String username) throws DBException {
        List<User> users = findBy(SQL__SELECT_USER_BY_LOGIN, username);
        return users.isEmpty() ? null : users.get(0);

    }

    @Override
    public void inverseLockState(String username) throws DBException {
        User user = findByLogin(username);
        execute(SQL__UPDATE_LOCK, String.valueOf(!user.isLocked()), String.valueOf(user.getId()));

    }

    @Override
    public long create(User user) throws DBException {
        return execute(SQL__INSERT_USER, user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(),
                String.valueOf(user.getRoleId()));
    }

    @Override
    public void delete(long userId) throws DBException {
        execute(SQL__DELETE_USER_BY_LOGIN, String.valueOf(userId));
    }
}
