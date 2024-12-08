package com.example.rrcb.repository;

import com.example.rrcb.model.entity.Role;
import com.example.rrcb.model.entity.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleNameEnum role);
}
