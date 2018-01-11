package ua.nure.lukianova.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.entity.Answer;

import java.sql.*;

public class JdbcAnswerDAO extends JdbcAbstractDAO<Answer> implements AnswerDAO{


    private static String sqlQuery;

    @Override
    public void create(Answer answer){
        execute(sqlQuery, answer.getText(), String.valueOf(answer.isCorrect()));
    }

    @Override
    protected Answer createEntity(ResultSet resultSet) throws SQLException {
        return null;//???????
    }

    public static void setSqlInsert( String sql){
        sqlQuery = sql;
    }
}
