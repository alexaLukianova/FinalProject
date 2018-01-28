package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.bean.TestResultBean;
import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface ResultService {

    long create(Result result) throws DBException;

    Result findByUserAndTestId(long userId, long testId) throws DBException;

    void update(long id, int result) throws DBException;

    List<TestResultBean> findResultByUserId(long id) throws  DBException;
}
