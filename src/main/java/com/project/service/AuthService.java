package com.project.service;

import com.project.dao.UserDAO;
import com.project.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class AuthService {
    private final UserDAO userDao;

    public AuthService() {
        this.userDao = new UserDAO();
    }

    public User login(String username, String password) {
        Optional<User> userOptional = userDao.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (verifyPassword(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public boolean register(User user, String rawPassword) {
        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        user.setPassword(hashPassword(rawPassword));
        return userDao.createUser(user);
    }

    private String hashPassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    private boolean verifyPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}