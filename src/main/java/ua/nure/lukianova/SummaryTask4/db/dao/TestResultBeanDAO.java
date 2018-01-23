package ua.nure.lukianova.SummaryTask4.db.dao;

import ua.nure.lukianova.SummaryTask4.db.bean.TestResultBean;
import ua.nure.lukianova.SummaryTask4.exception.DBException;

import java.util.List;

public interface TestResultBeanDAO {

    List<TestResultBean> findByUserId(long id) throws DBException;
}
