package ua.nure.lukianova.dao;

import ua.nure.lukianova.entity.Test;

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
    public void create(Test test) {
        execute(SQL__INSERT_INTO_TESTS_NEW_TEST, test.getName(), test.getComplexity(), test.getSubject());
    }

    @Override
    protected Test createEntity(ResultSet resultSet) throws SQLException {
        return new Test(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("subject"),
                resultSet.getString("complexity"));
    }
}
