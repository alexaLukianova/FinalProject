package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface ResultDAO {

    long create(Result result) throws DBException;

    Result findById(long resultId) throws DBException;

    void update(long id, int result) throws DBException;

    List<Result> findByTestId(long testId) throws  DBException;
}
