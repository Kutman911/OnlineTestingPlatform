package com.project.model;

public class Teacher extends User {
    public Teacher(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public String getRole() {
        return "TEACHER";
    }


    public void createTest() {

    }
}