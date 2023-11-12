package com.example.rrcb.model.entity;


import com.example.rrcb.model.entity.enums.RoleNameEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleNameEnum name;

    public Role() {
    }

    public Role(RoleNameEnum name) {
        this.name = name;
    }

    public RoleNameEnum getName() {
        return name;
    }

    public Role setName(RoleNameEnum name) {
        this.name = name;
        return this;
    }
}
