package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTestDAO extends JdbcAbstractDAO<Test> implements TestDAO {

    private static final String SQL__INSERT_INTO_TESTS_NEW_TEST =
            "INSERT INTO TESTS (NAME, COMPLEXITY_ID, SUBJECT, DURATION) VALUES (?, ?, ?, ?)";
    private static final String SQL__DELETE_TEST_BY_LOGIN =
            "DELETE FROM TESTS WHERE ID=(?)";
    private static final String SQL__SELECT_TEST_BY_ID =
            "SELECT * FROM TESTS WHERE ID=(?)";
    private static final String SQL__UPDATE = "UPDATE TESTS SET NAME = ?, SUBJECT = ?, COMPLEXITY_ID =?, DURATION = ? WHERE ID = ?";
    private static final String SQL__SELECT_ALL_TEST = "SELECT * FROM TESTS";

    {
        sqlSelectAll = SQL__SELECT_ALL_TEST;
        sqlSelectById = SQL__SELECT_TEST_BY_ID;
    }


    @Override
    public void delete(long testId) throws DBException {
        execute(SQL__DELETE_TEST_BY_LOGIN, String.valueOf(testId));
    }


    @Override
    public Test update(Test test) throws DBException {
         execute(SQL__UPDATE, test.getName(), test.getSubject(), String.valueOf(test.getComplexityId()),
                String.valueOf(test.getDuration()), String.valueOf(test.getId()));
         return findById(test.getId());
    }



    @Override
    public long create(Test test) throws DBException {
        return execute(SQL__INSERT_INTO_TESTS_NEW_TEST, test.getName(), String.valueOf(test.getComplexityId()),
                test.getSubject(), String.valueOf(test.getDuration()));
    }


    @Override
    protected Test extractEntity(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        test.setId(resultSet.getLong(Fields.ENTITY_ID));
        test.setName(resultSet.getString(Fields.TEST_NAME));
        test.setSubject(resultSet.getString(Fields.TEST_SUBJECT));
        test.setComplexityId(resultSet.getInt(Fields.TEST_COMPLEXITY));
        test.setDuration(resultSet.getLong(Fields.TEST_DURATION));
        return test;
    }
}
