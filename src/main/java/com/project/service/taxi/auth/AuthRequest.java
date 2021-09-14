package com.project.service.taxi.auth;

import javax.validation.constraints.Size;

public class AuthRequest {
    @Size(min = 3,max = 20,message = "должен состоять из 3-20 символов")
    private String login;
    @Size(min = 3,max = 50,message = "должен состоять из 3-50 символов")
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
