package com.example.rrcb.model.view;

import com.example.rrcb.model.entity.Role;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel {

    private Long id;

    private String username;

    private String fullName;

    private String email;

    private Role role;


    public UserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserViewModel setRole(Role role) {
        this.role = role;
        return this;
    }


}
