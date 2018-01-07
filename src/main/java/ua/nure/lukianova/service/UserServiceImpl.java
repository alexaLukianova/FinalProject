package ua.nure.lukianova.service;

import ua.nure.lukianova.dao.UserDAO;
import ua.nure.lukianova.dao.JdbcUserDAO;
import ua.nure.lukianova.entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new JdbcUserDAO();
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
