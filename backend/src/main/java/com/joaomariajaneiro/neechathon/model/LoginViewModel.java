package com.joaomariajaneiro.neechathon.model;

public class LoginViewModel {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public LoginViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginViewModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
