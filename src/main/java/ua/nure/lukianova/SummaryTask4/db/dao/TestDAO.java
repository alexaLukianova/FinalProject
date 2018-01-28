package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface TestDAO {

    long create (Test test) throws DBException;

    List<Test> findAll() throws DBException;

    void delete(long testId) throws DBException;

    Test findById(long id) throws DBException;

    Test update(Test test) throws DBException;

}
