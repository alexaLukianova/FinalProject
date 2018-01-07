package ua.nure.lukianova.service;

import ua.nure.lukianova.dao.JdbcTestDAO;
import ua.nure.lukianova.dao.TestDAO;
import ua.nure.lukianova.entity.Test;

import java.util.List;

public class TestServiceImpl implements TestService {

    private TestDAO testDAO;

    public TestServiceImpl() {
        testDAO = new JdbcTestDAO();
    }

    @Override
    public List<Test> findAllTests() {
        return testDAO.findAllTests();
    }
}
