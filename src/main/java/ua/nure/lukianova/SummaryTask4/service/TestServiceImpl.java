package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.dao.TestDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.JdbcTestDAO;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public class TestServiceImpl implements TestService {

    private TestDAO testDAO;

    public TestServiceImpl() {
        testDAO = new JdbcTestDAO();
    }

    @Override
    public List<Test> findAllTests() throws DBException {
        return testDAO.findAll();
    }

    @Override
    public long create(Test test) throws DBException {
        return testDAO.create(test);
    }

}
