package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.dao.AnswerDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.JdbcAnswerDAO;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private AnswerDAO answerDAO;

    public AnswerServiceImpl() {
        this.answerDAO = new JdbcAnswerDAO();
    }

    @Override
    public long create(Answer answer) throws DBException {
        return answerDAO.create(answer);
    }

    @Override
    public List<Answer> findByQuestionId(long id) throws DBException {
        return answerDAO.findByQuestionId(id);
    }

    @Override
    public void update(Answer answer) throws DBException {
         answerDAO.update(answer);
    }

    @Override
    public Answer findById(long id) throws DBException {
        return answerDAO.findById(id);
    }

    @Override
    public List<Answer> findCorrectByQuestionId(long id) throws DBException {
        return answerDAO.findCorrectByQuestionId(id);
    }
}

