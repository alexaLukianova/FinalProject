package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface TestService {

    List<Test> findAllTests() throws DBException;

    long create(Test test) throws DBException;

    void delete(long testId) throws DBException;

    Test findById(long testId) throws DBException;

    Test update(Test test) throws DBException;
}
