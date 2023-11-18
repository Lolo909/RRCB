package com.example.rrcb.service;

import com.example.rrcb.model.entity.Role;
import com.example.rrcb.model.entity.User;
import com.example.rrcb.model.entity.enums.RoleNameEnum;
import com.example.rrcb.model.service.UserServiceModel;
import com.example.rrcb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleServiceImpl userRoleServiceImpl;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleServiceImpl userRoleServiceImpl) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleServiceImpl = userRoleServiceImpl;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        final Role userRole = this.userRoleServiceImpl.getRole(RoleNameEnum.USER);
        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));

/*
        final RoleNameEnum userRole = RoleNameEnum.USER;
        final Role role = new Role().setName(userRole);
        final List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);
*/
        //user.setRoles(List.of());
        //user.setRoles(new ArrayList<Role>(Collections.<Role>singletonList(userRole)));
        //user.setRoles(RoleNameEnum.USER.name());//?
        userRepository.save(user);
    }


    @Override
    public UserServiceModel findById(Long id) {
        return userRepository
                .findById(id)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }



}
