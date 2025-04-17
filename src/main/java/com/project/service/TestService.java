package com.project.service;

import com.project.dao.TestDAO;
import com.project.model.Test;
import java.util.List;

public class TestService {
    private final TestDAO testDao;

    public TestService() {
        this.testDao = new TestDAO();
    }

    public boolean createTest(Test test) {
        return testDao.createTest(test);
    }

    public List<Test> getAllTests() {
        return testDao.findAll();
    }

    public List<Test> getTestsByCreator(int createdBy) {
        return testDao.findByCreator(createdBy);
    }

    public List<Test> getPublishedTests() {
        return testDao.findPublishedTests();
    }

    public Test getTestById(int id) {
        return testDao.findById(id).orElse(null);
    }

    public boolean updateTest(Test test) {
        return testDao.updateTest(test);
    }

    public boolean deleteTest(int id) {
        return testDao.deleteTest(id);
    }

    public boolean publishTest(int testId) {
        Test test = getTestById(testId);
        if (test != null) {
            test.setPublished(true);
            return updateTest(test);
        }
        return false;
    }
}