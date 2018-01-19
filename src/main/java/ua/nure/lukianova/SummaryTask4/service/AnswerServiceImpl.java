package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.dao.AnswerDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.JdbcAnswerDAO;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public class AnswerServiceImpl implements AnswerService{

    private AnswerDAO answerDAO;

    public AnswerServiceImpl() {
        this.answerDAO = new JdbcAnswerDAO();
    }

    @Override
    public long create(Answer answer) throws DBException {
        return answerDAO.create(answer);
    }

    @Override
    public List<Answer> findAnswersByQuestionId(long id) throws DBException {
        return answerDAO.findAnswersByQuestionId(id);
    }
}

