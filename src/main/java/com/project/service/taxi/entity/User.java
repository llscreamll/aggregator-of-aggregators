package com.project.service.taxi.entity;

import com.project.service.taxi.entity.erole.RoleUser;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleUser role;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE, orphanRemoval = true,optional = false)
    private Order order;

    public User() {
    }

    public User(String login, String password, RoleUser role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
