package ua.nure.lukianova.dao;

import ua.nure.lukianova.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

}
