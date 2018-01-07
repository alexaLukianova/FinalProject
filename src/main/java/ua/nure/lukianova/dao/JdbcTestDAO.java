package ua.nure.lukianova.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.entity.Test;
import ua.nure.lukianova.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTestDAO implements TestDAO {

    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static Logger LOGGER = LoggerFactory.getLogger(JdbcTestDAO.class);

    private static final String SQL__INSERT_INTO_TESTS_NEW_TEST =
            "INSERT INTO TESTS (NAME, COMPLEXITY, SUBJECT) VALUES (?, ?, ?)";
    private static final String SQL__SELECT_ALL_TESTS = "SELECT * FROM TESTS";

    @Override
    public void create(Test test) {
        execute(SQL__INSERT_INTO_TESTS_NEW_TEST, test.getName(), test.getComplexity(), test.getSubject());
    }

    @Override
    public List<Test> findAllTests() {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet result;
        List<Test> tests = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL__SELECT_ALL_TESTS);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                tests.add(createTest(result));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);

        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
        return tests;
    }

    private Test createTest(ResultSet resultSet) throws SQLException {
        return new Test(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("subject"),
                resultSet.getString("complexity"));
    }

    private void execute(String sql, String... parameters) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            int index = 0;
            for (String parameter : parameters) {
                preparedStatement.setString(++index, parameter);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
    }


}
