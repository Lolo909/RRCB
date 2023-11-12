package com.example.rrcb.model.service;



import com.example.rrcb.model.entity.Role;

import java.util.List;
import java.util.Set;

public class UserServiceModel {

    private Long id;

    private String username;

    private String fullName;

    private String password;

    private String email;

    private List<Role> roles;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }
}
