package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.dao.JdbcUserDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.UserDAO;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new JdbcUserDAO();
    }

    @Override
    public List<User> findAll() throws DBException{
        return userDAO.findAll();
    }

    @Override
    public User findUserByLogin(String username) throws DBException {
        return userDAO.findUserByLogin(username);
    }
}
