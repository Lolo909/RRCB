package com.example.rrcb.model.entity;


import com.example.rrcb.model.entity.enums.RoleNameEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleNameEnum name;

//    @ManyToMany(fetch = FetchType.EAGER)//fetch = FetchType.EAGER
//    private List<User> users = new ArrayList<>();

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
