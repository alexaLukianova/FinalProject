package ua.nure.lukianova.service;

import ua.nure.lukianova.entity.Test;

import java.util.List;

public interface TestService {

    List<Test> findAllTests();

    void create(Test test);
}
