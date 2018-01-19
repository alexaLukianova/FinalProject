package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface AnswerDAO {

   long create(Answer answer) throws DBException;

   List<Answer> findAnswersByQuestionId(long id) throws DBException;
}
