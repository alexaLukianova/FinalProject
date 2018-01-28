package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface QuestionService {

    long create(Question question) throws DBException;

    List<Question> findByTestId(long id) throws DBException;

    boolean delete(long id) throws  DBException;

    void update(Question question) throws DBException;

    Question findById(long id) throws DBException;
}
