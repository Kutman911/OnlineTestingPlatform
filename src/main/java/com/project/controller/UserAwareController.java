package com.project.controller;

import com.project.model.User;

public interface UserAwareController {
    void setUser(User user);
}