package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.List;

public class JdbcAnswerDAO extends JdbcAbstractDAO<Answer> implements AnswerDAO {


    private static String sqlQuery;

    private static final String SQL__INSERT_INTO_ANSWERS_NEW_ANSWER =
            "INSERT INTO ANSWERS (TEXT, CORRECT, QUESTION_ID) VALUES (?, ?, ?)";
    private static final String SQL__SELECT_ANSWERS_BY_QUESTION_ID =
            "SELECT*FROM ANSWERS WHERE QUESTION_ID = ?";
    private static final String SQL__UPDATE = "UPDATE ANSWERS SET TEXT = ?, CORRECT = ? WHERE ID = ?";

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
    public long update(long id, String text, boolean correct) throws DBException {
        return execute(SQL__UPDATE, text, String.valueOf(correct), String.valueOf(id));
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

    public static void setSqlInsert(String sql) {
        sqlQuery = sql;
    }
}
