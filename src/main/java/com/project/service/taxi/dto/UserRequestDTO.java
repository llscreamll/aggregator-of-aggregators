package com.project.service.taxi.dto;


import com.project.service.taxi.entity.erole.RoleUser;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank(message = "не должен быть пустым")
    @Size(min = 3,max = 20,message = "должен состоять из 3-20 символов")
    private String login;
    @NotBlank(message = "не должен быть пустым")
    @Size(min = 3,max = 50,message = "должен состоять из 3-50 символов")
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
