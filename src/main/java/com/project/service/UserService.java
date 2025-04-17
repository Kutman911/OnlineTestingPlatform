package com.project.service;

import com.project.dao.UserDAO;
import com.project.model.User;
import java.util.List;

public class UserService {
    private final UserDAO userDao = new UserDAO();

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    public boolean createUser(User user) {
        return userDao.createUser(user);
    }

    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }
}