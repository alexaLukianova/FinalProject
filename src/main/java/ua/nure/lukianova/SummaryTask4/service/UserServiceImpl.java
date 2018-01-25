package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.bean.TestResultBean;
import ua.nure.lukianova.SummaryTask4.db.dao.JdbcTestResultBeanDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.JdbcUserDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.TestResultBeanDAO;
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
    public List<User> findAll() throws DBException {
        return userDAO.findAll();
    }

    @Override
    public User findByLogin(String username) throws DBException {
        return userDAO.findByLogin(username);
    }

    @Override
    public void inverseLockState(String username) throws DBException {
        userDAO.inverseLockState(username);
    }

    @Override
    public long create(User user) throws DBException {
        return userDAO.create(user);
    }

    @Override
    public void delete(long userId) throws DBException {
        userDAO.delete(userId);
    }


}
