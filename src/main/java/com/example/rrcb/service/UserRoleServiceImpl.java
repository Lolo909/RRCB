package com.example.rrcb.service;

import com.example.rrcb.model.entity.Role;
import com.example.rrcb.model.entity.enums.RoleNameEnum;
import com.example.rrcb.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public Role getRole(RoleNameEnum role){
        return this.userRoleRepository.findByName(role);
    }
}
