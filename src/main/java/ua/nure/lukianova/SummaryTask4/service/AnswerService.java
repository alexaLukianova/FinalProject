package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface AnswerService {

    long create(Answer answer) throws DBException;

    List<Answer> findAnswersByQuestionId(long id) throws DBException;
}
