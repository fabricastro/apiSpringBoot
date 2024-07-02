package com.example.products.security;

import java.io.Serializable;

public class AuthenticationReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    // Default constructor necessary for JSON deserialization
    public AuthenticationReq() {
    }

    public AuthenticationReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUser() {
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
}
