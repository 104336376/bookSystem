package com.book.pojo;

public class User {
    private String id;
    private String userName;
    private String password;

    public User(String id, String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


