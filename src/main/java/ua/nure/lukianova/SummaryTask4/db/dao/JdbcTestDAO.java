package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTestDAO extends JdbcAbstractDAO<Test> implements TestDAO {

    private static final String SQL__INSERT_INTO_TESTS_NEW_TEST =
            "INSERT INTO TESTS (NAME, COMPLEXITY, SUBJECT) VALUES (?, ?, ?)";
    private static final String SQL__SELECT_ALL_TEST = "SELECT * FROM TESTS";

    {
        sqlSelectAll = SQL__SELECT_ALL_TEST;
    }

    @Override
    public long create(Test test) throws DBException{
        return execute(SQL__INSERT_INTO_TESTS_NEW_TEST, test.getName(), test.getComplexity(), test.getSubject());
    }

    @Override
    protected Test extractEntity(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        test.setId(resultSet.getLong(Fields.ENTITY_ID));
        test.setName(resultSet.getString(Fields.TEST_NAME));
        test.setSubject(resultSet.getString(Fields.TEST_SUBJECT));
        test.setComplexity(resultSet.getString(Fields.TEST_COMPLEXITY));
        test.setTime(resultSet.getLong(Fields.TEST_TIME));
        return test;
    }
}
