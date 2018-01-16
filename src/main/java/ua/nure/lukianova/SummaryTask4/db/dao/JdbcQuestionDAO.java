package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;

public class JdbcQuestionDAO extends JdbcAbstractDAO<Question> implements QuestionDAO {

    private static final String SQL__INSERT_INTO_QUESTIONS = "INSERT INTO QUESTIONS (QUESTION) VALUES (?)";
    private static final String SQL__SELECT_FROM_QUESTIONS_BY_TEXT = "SELECT * FROM QUESTIONS WHERE TEXT =";

    @Override
    public void create(Question question) throws DBException{
        execute(SQL__INSERT_INTO_QUESTIONS, question.getQuestion());
       // setAnswers(question);
    }

    private void setAnswers(Question question){
        Formatter sql = new Formatter();
        long id = 1l; //readId(question);
        sql.format("INSERT INTO ANSWERS (TEXT, CORRECT, QUESTION_ID) VALUES (?, ?, %s)", id);
        JdbcAnswerDAO.setSqlInsert(sql.toString());
    }

    private long readId(Question question) throws DBException {
        sqlSelectAll= SQL__SELECT_FROM_QUESTIONS_BY_TEXT;
        return findAll().get(0).getId();
    }


    @Override
    protected Question extractEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

}
