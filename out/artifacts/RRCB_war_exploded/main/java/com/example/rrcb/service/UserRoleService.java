package com.example.rrcb.service;

import com.example.rrcb.model.entity.Role;
import com.example.rrcb.model.entity.enums.RoleNameEnum;
import com.example.rrcb.model.service.UserServiceModel;

public interface UserRoleService {

    Role getRole(RoleNameEnum role);
}
