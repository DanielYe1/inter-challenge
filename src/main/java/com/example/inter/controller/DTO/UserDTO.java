package com.example.inter.controller.DTO;

import com.example.inter.model.User;

public class UserDTO {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User toApplicationUser() {
        return new User(name, email);
    }
}
