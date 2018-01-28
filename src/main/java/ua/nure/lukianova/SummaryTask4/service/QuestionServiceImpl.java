package ua.nure.lukianova.SummaryTask4.service;

import ua.nure.lukianova.SummaryTask4.db.dao.JdbcQuestionDAO;
import ua.nure.lukianova.SummaryTask4.db.dao.QuestionDAO;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDAO questionDAO;

    public QuestionServiceImpl() {
        questionDAO = new JdbcQuestionDAO();
    }

    @Override
    public long create(Question question) throws DBException {
        return questionDAO.create(question);
    }

    @Override
    public boolean delete(long id) throws DBException {
        return questionDAO.delete(id);
    }

    @Override
    public void update(Question question) throws DBException {
         questionDAO.update(question);
    }

    @Override
    public Question findById(long id) throws DBException {
        return questionDAO.findById(id);
    }

    @Override
    public List<Question> findByTestId(long id) throws DBException {
        return questionDAO.findByTestId(id);
    }


}
