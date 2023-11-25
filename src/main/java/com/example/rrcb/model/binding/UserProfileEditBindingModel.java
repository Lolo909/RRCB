package com.example.rrcb.model.binding;

import jakarta.validation.constraints.Size;

public class UserProfileEditBindingModel {

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters!")
    private String username;

    @Size(min = 3)
    private String fullName;

    private String email;


    public UserProfileEditBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserProfileEditBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileEditBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileEditBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
