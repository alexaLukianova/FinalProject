package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.bean.TestResultBean;
import ua.nure.lukianova.SummaryTask4.db.dao.JdbcResultDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.JdbcTestResultBeanDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.ResultDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.TestResultBeanDAO;
import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public class ResultServiceImpl  implements  ResultService{

    private ResultDAO resultDAO;
    private TestResultBeanDAO testResultBeanDAO;

    public ResultServiceImpl() {
        resultDAO = new JdbcResultDAO();
        testResultBeanDAO = new JdbcTestResultBeanDAO();
    }

    @Override
    public Result findById(long resultId) throws DBException {
        return resultDAO.findById(resultId);
    }

    @Override
    public void update(long id, int result) throws DBException {
         resultDAO.update(id,result);
    }

    @Override
    public long create(Result result) throws DBException {
        return resultDAO.create(result);
    }

    @Override
    public List<TestResultBean> findByUserId(long id) throws DBException {
        return testResultBeanDAO.findByUserId(id);
    }

    @Override
    public List<Result> findByTestId(long testId) throws DBException {
        return resultDAO.findByTestId(testId);
    }

}
