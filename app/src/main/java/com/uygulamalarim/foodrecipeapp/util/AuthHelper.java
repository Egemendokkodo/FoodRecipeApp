package com.uygulamalarim.foodrecipeapp.util;

import java.util.List;

public class AuthHelper {
    String email,username,password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public AuthHelper(String username, String email , String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }
    public AuthHelper(){

    }
}
