package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

public interface ResultDAO {

    long create(Result result) throws DBException;

    Result findByUserAndTestId(long userId, long testId) throws DBException;

    long update(long id, int result) throws DBException;
}
