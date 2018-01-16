package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface UserDAO {

    List<User> findAll() throws DBException;

    User findUserByLogin(String username) throws DBException;

}
