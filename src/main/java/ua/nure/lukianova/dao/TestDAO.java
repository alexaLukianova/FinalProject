package ua.nure.lukianova.dao;

import ua.nure.lukianova.entity.Test;

import java.util.List;

public interface TestDAO {

    void create (Test test);

    List<Test> findAllTests();
}
