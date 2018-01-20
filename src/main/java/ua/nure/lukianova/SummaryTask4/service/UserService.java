package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface UserService {

    List<User> findAll() throws DBException;

    User findByLogin(String username) throws DBException;

    void inverseLockState(String username) throws DBException;
}
