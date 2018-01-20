package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcQuestionDAO extends JdbcAbstractDAO<Question> implements QuestionDAO {

    private static final String SQL__INSERT_INTO_QUESTIONS = "INSERT INTO QUESTIONS (TEXT, TEST_ID) VALUES (?, ?)";
    private static final String SQL__SELECT_FROM_QUESTIONS_BY_TEXT = "SELECT * FROM QUESTIONS WHERE TEXT = ?";
    private static final String SQL__SELECT_FROM_QUESTIONS_BY_TEST_ID = "SELECT * FROM QUESTIONS WHERE TEST_ID = ?";
    private static final String SQL__DELETE = "DELETE FROM QUESTIONS WHERE ID = ?";
    private static final String SQL__UPDATE = "UPDATE QUESTIONS SET TEXT = ? WHERE ID = ?";

    @Override
    public long create(Question question) throws DBException {
        return execute(SQL__INSERT_INTO_QUESTIONS,
                question.getText(),
                String.valueOf(question.getTestId()));
    }

    @Override
    public List<Question> findByTestId(long id) throws DBException {
        return findBy(SQL__SELECT_FROM_QUESTIONS_BY_TEST_ID, String.valueOf(id));
    }

    @Override
    public boolean delete(long id) throws DBException {
        return execute(SQL__DELETE, String.valueOf(id)) != -1;
    }

    @Override
    public long update(long id, String text) throws DBException {
        return execute(SQL__UPDATE, text, String.valueOf(id));
    }


    private long readId(Question question) throws DBException {
        sqlSelectAll = SQL__SELECT_FROM_QUESTIONS_BY_TEXT;
        return findAll().get(0).getId();
    }


    @Override
    protected Question extractEntity(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        question.setId(resultSet.getLong(Fields.ENTITY_ID));
        question.setText(resultSet.getString(Fields.QUESTION_TEXT));
        question.setTestId(resultSet.getLong(Fields.QUESTION_TEST_ID));
        return question;
    }

}
