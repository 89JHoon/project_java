package com.example.project_java.user.entity;

import com.example.project_java.user.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {
    @Setter
    private Long id;

    private String userName;
    private String password;
    private String nickname;
    private List<Role> roles;


    public User(String userName, String password, String nickname) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
        this.roles = new ArrayList<>();
        this.roles.add(Role.USER);
    }

    public void updateRoles(Role role) {
        this.roles.clear();
        this.roles.add(role);
    }
}
