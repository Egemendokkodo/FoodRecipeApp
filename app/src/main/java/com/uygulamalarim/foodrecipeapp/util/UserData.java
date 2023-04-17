package com.uygulamalarim.foodrecipeapp.util;

public class UserData {
    private static final UserData instance = new UserData();
    private String username;

    private UserData() {}

    public static UserData getInstance() {
        return instance;
    }

    public String getUsername() {
        return username != null ? username : "";
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
