package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.*;

public class JdbcAnswerDAO extends JdbcAbstractDAO<Answer> implements AnswerDAO{


    private static String sqlQuery;

    @Override
    public void create(Answer answer) throws DBException{
        execute(sqlQuery, answer.getText(), String.valueOf(answer.isCorrect()));
    }

    @Override
    protected Answer extractEntity(ResultSet resultSet) throws SQLException {
        return null;//???????
    }

    public static void setSqlInsert( String sql){
        sqlQuery = sql;
    }
}
