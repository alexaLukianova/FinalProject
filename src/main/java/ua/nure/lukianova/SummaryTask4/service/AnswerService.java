package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface AnswerService {

    long create(Answer answer) throws DBException;

    List<Answer> findByQuestionId(long id) throws DBException;

    long update(long id, String text, boolean correct) throws DBException;

    Answer findById(long id) throws DBException;

    List<Answer> findCorrectByQuestionId (long id) throws DBException;
}
