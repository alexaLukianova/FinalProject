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
    public long update(long id, String text, boolean correct) throws DBException {
        return answerDAO.update(id, text, correct);
    }
}

