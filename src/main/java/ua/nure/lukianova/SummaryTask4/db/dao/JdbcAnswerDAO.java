package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.List;

public class JdbcAnswerDAO extends JdbcAbstractDAO<Answer> implements AnswerDAO {


    private static final String SQL__INSERT_INTO_ANSWERS_NEW_ANSWER =
            "INSERT INTO ANSWERS (TEXT, CORRECT, QUESTION_ID) VALUES (?, ?, ?)";
    private static final String SQL__SELECT_ANSWERS_BY_QUESTION_ID =
            "SELECT*FROM ANSWERS WHERE QUESTION_ID = ?";
    private static final String SQL__UPDATE = "UPDATE ANSWERS SET TEXT = ?, CORRECT = ? WHERE ID = ?";
    private static final String SQL__SELECT_ALL_ANSWERS = "SELECT * FROM ANSWERS";
    private static final String SQL__SELECT_CORRECT_ANSWERS_BY_QUESTION_ID =
            "SELECT*FROM ANSWERS WHERE QUESTION_ID = ? AND CORRECT=TRUE";
    private static final String SQL__SELECT_ANSWER_BY_ID = "SELECT * FROM ANSWERS WHERE ID = ?";

    {
        sqlSelectAll = SQL__SELECT_ALL_ANSWERS;
        sqlSelectById = SQL__SELECT_ANSWER_BY_ID;
    }


    @Override
    public long create(Answer answer) throws DBException {
        return execute(SQL__INSERT_INTO_ANSWERS_NEW_ANSWER,
                answer.getText(),
                String.valueOf(answer.isCorrect()),
                String.valueOf(answer.getQuestionId()));
    }

    @Override
    public List<Answer> findByQuestionId(long id) throws DBException {
        return findBy(SQL__SELECT_ANSWERS_BY_QUESTION_ID, String.valueOf(id));
    }

    @Override
    public List<Answer> findCorrectByQuestionId(long id) throws DBException {
        return findBy(SQL__SELECT_CORRECT_ANSWERS_BY_QUESTION_ID, String.valueOf(id));
    }

    @Override
    public void update(Answer answer) throws DBException {
        execute(SQL__UPDATE, answer.getText(), String.valueOf(answer.isCorrect()), String.valueOf(answer.getId()));
    }


    @Override
    protected Answer extractEntity(ResultSet resultSet) throws SQLException {
        Answer answer = new Answer();
        answer.setId(resultSet.getLong(Fields.ENTITY_ID));
        answer.setText(resultSet.getString(Fields.ANSWER_TEXT));
        answer.setCorrect(resultSet.getBoolean(Fields.ANSWER_CORRECT));
        answer.setQuestionId(resultSet.getLong(Fields.ANSWER_QUESTION_ID));
        return answer;
    }

}
