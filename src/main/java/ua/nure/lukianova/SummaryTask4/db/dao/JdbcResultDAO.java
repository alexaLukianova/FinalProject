package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcResultDAO extends JdbcAbstractDAO<Result> implements ResultDAO {

    private static final String SQL__INSERT_INTO_RESULTS =
            "INSERT INTO RESULTS (TEST_ID, RESULT, START_TIME, USER_ID) VALUES (?, ?, ?, ?)";
    private static final String SQL__SELECT_ALL_RESULTS = "SELECT * FROM RESULTS";
    private static final String SQL__SELECT_RESULT_BY_ID = "SELECT * FROM RESULTS WHERE ID = ?";
    private static final String SQL__SELECT_RESULT_BY_USER_AND_TEST_ID = "SELECT * FROM RESULTS WHERE USER_ID = ? AND TEST_ID = ?";
    private static final String SQL__UPDATE = "UPDATE RESULTS SET RESULT = ? WHERE ID = ?";


    {
        sqlSelectAll = SQL__SELECT_ALL_RESULTS;
        sqlSelectById = SQL__SELECT_RESULT_BY_ID;
    }


    @Override
    protected Result extractEntity(ResultSet resultSet) throws SQLException {
        Result result = new Result();
        result.setId(resultSet.getLong(Fields.ENTITY_ID));
        result.setTestId(resultSet.getLong(Fields.RESULT_TEST_ID));
        result.setResult(resultSet.getInt(Fields.RESULT_RESULT));
        result.setStartTime(resultSet.getLong(Fields.RESULT_START_TIME));
        result.setUserId(resultSet.getLong(Fields.RESULT_USER_ID));
        return result;
    }

    @Override
    public long create(Result result) throws DBException {
        return execute(SQL__INSERT_INTO_RESULTS,
                String.valueOf(result.getTestId()),
                String.valueOf(result.getResult()),
                String.valueOf(result.getStartTime()),
                String.valueOf(result.getUserId()));
    }

    @Override
    public Result findByUserAndTestId(long userId, long testId) throws DBException {
        List<Result> results = findBy(SQL__SELECT_RESULT_BY_USER_AND_TEST_ID, String.valueOf(userId), String.valueOf(testId));
        return results.isEmpty() ? null : results.get(0);

    }

    @Override
    public long update(long id, int result) throws DBException {
        return execute(SQL__UPDATE, String.valueOf(result), String.valueOf(id));
    }


}
