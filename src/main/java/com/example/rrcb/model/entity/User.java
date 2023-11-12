package com.example.rrcb.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {


    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String password;

    @Column()
    private String email;

    @Column//(name = "roles")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();


    public User() {
    }


    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public User setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }
}
