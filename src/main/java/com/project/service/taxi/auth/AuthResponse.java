package com.project.service.taxi.auth;

public class AuthResponse {
    private Long id;
    private String token;

    public AuthResponse(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
