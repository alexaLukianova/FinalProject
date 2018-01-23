package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.dao.JdbcResultDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.ResultDAO;
import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

public class ResultServiceImpl  implements  ResultService{

    private ResultDAO resultDAO;

    public ResultServiceImpl() {
        resultDAO = new JdbcResultDAO();
    }

    @Override
    public Result findByUserAndTestId(long userId, long testId) throws DBException {
        return resultDAO.findByUserAndTestId(userId,testId);
    }

    @Override
    public long update(long id, int result) throws DBException {
        return resultDAO.update(id,result);
    }

    @Override
    public long create(Result result) throws DBException {
        return resultDAO.create(result);
    }
}
