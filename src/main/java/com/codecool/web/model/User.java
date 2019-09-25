package com.codecool.web.model;

import com.codecool.web.model.enums.Role;

import java.util.Objects;

public class User extends AbstractModel {
    private String name;
    private String email;
    private Role role;
    private String password;
    
    
    public User(Integer id, String name, String email, Role role, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }
    
    
    public User(Integer id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
