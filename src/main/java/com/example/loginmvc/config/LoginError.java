package com.example.loginmvc.config;

public class LoginError {

    private final String message;

    public LoginError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
