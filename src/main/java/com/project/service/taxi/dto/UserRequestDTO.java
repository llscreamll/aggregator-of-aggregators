package com.project.service.taxi.dto;


import com.project.service.taxi.entity.erole.RoleUser;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserRequestDTO {

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    public UserRequestDTO() {
    }

    public UserRequestDTO( String login,String password, RoleUser role) {

        this.login = login;
        this.password = password;
        this.role = role;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
