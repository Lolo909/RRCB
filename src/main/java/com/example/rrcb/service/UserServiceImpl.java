package com.example.rrcb.service;

import com.example.rrcb.model.binding.UserProfileEditBindingModel;
import com.example.rrcb.model.entity.Role;
import com.example.rrcb.model.entity.User;
import com.example.rrcb.model.entity.enums.RoleNameEnum;
import com.example.rrcb.model.service.UserServiceModel;
import com.example.rrcb.model.view.OrderViewModel;
import com.example.rrcb.model.view.UserViewModel;
import com.example.rrcb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.rrcb.model.entity.enums.RoleNameEnum.ADMIN;
import static com.example.rrcb.model.entity.enums.RoleNameEnum.USER;

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

    @Override
    public List<UserViewModel> findAllUsersView() {
        return userRepository.findAll().stream().map(user -> {
            UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);

            List<String> roleList = user.getRoles().stream().map(role -> {
                if (role.getName().equals(ADMIN)) {
                    return "ADMIN";
                } else {
                    return "USER";
                }
            }).collect(Collectors.toList());

            if (roleList.contains("ADMIN")) {
                userViewModel.setRole(userRoleServiceImpl.getRole(ADMIN));
            } else {
                userViewModel.setRole(userRoleServiceImpl.getRole(USER));
            }

            return userViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changeRole(Long id) {
        User user = userRepository.findById(id).orElse(null);
        List<String> roleList = user.getRoles().stream().map(role -> {
            if (role.getName().equals(ADMIN)) {
                return "ADMIN";
            } else {
                return "USER";
            }
        }).collect(Collectors.toList());

        if (roleList.contains("ADMIN")) {
            user.getRoles().removeIf(userRoleEntity -> userRoleEntity.getName().equals(ADMIN));
        } else {
            user.getRoles().add(userRoleServiceImpl.getRole(ADMIN));
        }

        userRepository.saveAndFlush(user);
    }

    @Override
    public UserViewModel getUserViewByUsername(String name) {
        User user = userRepository.findByUsername(name).orElse(null);
        UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);

        List<String> roleList = user.getRoles().stream().map(role -> {
            if (role.getName().equals(ADMIN)) {
                return "ADMIN";
            } else {
                return "USER";
            }
        }).collect(Collectors.toList());

        if (roleList.contains("ADMIN")) {
            userViewModel.setRole(userRoleServiceImpl.getRole(ADMIN));
        } else {
            userViewModel.setRole(userRoleServiceImpl.getRole(USER));
        }

        return userViewModel;
    }

    @Override
    public void editProfile(Long id, UserProfileEditBindingModel userProfileEditBindingModel) {
        User userForEdit = userRepository.findById(id).orElse(null);

        userForEdit.setUsername(userProfileEditBindingModel.getUsername())
                .setFullName(userProfileEditBindingModel.getFullName())
                .setEmail(userProfileEditBindingModel.getEmail());

        userRepository.saveAndFlush(userForEdit);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return userRepository.findByUsername(name);
    }




}
