package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface AnswerDAO {

   long create(Answer answer) throws DBException;

   List<Answer> findByQuestionId(long id) throws DBException;

   List<Answer> findCorrectByQuestionId(long id) throws DBException;

   void update(Answer answer) throws DBException;

   void updateAll(List<Answer> answers) throws DBException;

   void createAll(List<Answer> answers) throws DBException;

   Answer findById(long id) throws DBException;
}
