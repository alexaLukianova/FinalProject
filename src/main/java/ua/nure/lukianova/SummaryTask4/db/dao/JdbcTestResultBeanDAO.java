package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.bean.TestResultBean;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class JdbcTestResultBeanDAO extends JdbcAbstractDAO<TestResultBean> implements TestResultBeanDAO {

    private static final String SQL__SELECT_BY_USER_ID = "SELECT TESTS.NAME, TESTS.SUBJECT, RESULTS.RESULT, " +
            "RESULTS.START_TIME FROM TESTS JOIN RESULTS ON TESTS.ID = RESULTS.TEST_ID JOIN USERS ON " +
            "RESULTS.USER_ID = USERS.ID WHERE USERS.ID = ?";


    @Override
    protected TestResultBean extractEntity(ResultSet resultSet) throws SQLException {
        TestResultBean testResultBean = new TestResultBean();
        testResultBean.setTestName(resultSet.getString(Fields.TEST_NAME));
        testResultBean.setTestSubject(resultSet.getString(Fields.TEST_SUBJECT));
        testResultBean.setResult(resultSet.getInt(Fields.RESULT_RESULT));
        testResultBean.setDate(new Date(resultSet.getLong(Fields.RESULT_START_TIME)));
        return testResultBean;
    }


    @Override
    public List<TestResultBean> findByUserId(long id) throws DBException {
        return findBy(SQL__SELECT_BY_USER_ID, String.valueOf(id));
    }
}
