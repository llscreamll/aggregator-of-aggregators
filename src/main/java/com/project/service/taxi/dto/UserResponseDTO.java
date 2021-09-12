package com.project.service.taxi.dto;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String login;

    public UserResponseDTO() {
    }
    public UserResponseDTO(Long id, String name, String email, String login) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
