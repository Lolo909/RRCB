package com.example.rrcb.service;


import com.example.rrcb.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findById(Long id);

}
