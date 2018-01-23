package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface QuestionDAO {

    long create(Question question) throws DBException;

    List<Question> findByTestId(long id) throws DBException;

    Question findById(long id) throws DBException;

    boolean delete(long id) throws DBException;

    long update(long id, String text) throws DBException;
}
