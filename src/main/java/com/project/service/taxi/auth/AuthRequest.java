package com.project.service.taxi.auth;

import javax.validation.constraints.NotEmpty;

public class AuthRequest {
    @NotEmpty(message = "Login не должен быть пустым")
    private String login;
    @NotEmpty(message = "password не должен быть пустым")
    private String password;
    public AuthRequest() {
    }
    public AuthRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
