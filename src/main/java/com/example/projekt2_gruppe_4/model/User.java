package com.example.projekt2_gruppe_4.model;

import java.time.LocalDateTime;

public class User {
    private int userId;
    private String name, email, passwordHash;
    private LocalDateTime userCreateDate;

    public User (int userId, String name, String email, String passwordHash, LocalDateTime userCreateDate){
        this.userId=userId;
        this.name=name;
        this.email=email;
        this.passwordHash=passwordHash;
        this.userCreateDate=userCreateDate;
    }

//to create user
    public User (String name, String email, String passwordHash){
        this.name=name;
        this.email=email;
        this.passwordHash=passwordHash;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(LocalDateTime userCreateDate) {
        this.userCreateDate = userCreateDate;
    }
}