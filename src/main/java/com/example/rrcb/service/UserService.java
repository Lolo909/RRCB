package com.example.rrcb.service;


import com.example.rrcb.model.binding.UserProfileEditBindingModel;
import com.example.rrcb.model.entity.User;
import com.example.rrcb.model.service.UserServiceModel;
import com.example.rrcb.model.view.UserViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findById(Long id);

    List<UserViewModel> findAllUsersView();

    void remove(Long id);

    void changeRole(Long id);

    UserViewModel getUserViewByUsername(String name);

    void editProfile(Long id, UserProfileEditBindingModel userProfileEditBindingModel);

    Optional<User> findUserByName(String name);
}
