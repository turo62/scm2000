package com.codecool.web.service;

import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getUsers(Connection connection) throws SQLException;
    
    User getUser(String mail) throws SQLException;
    
    String notValidated(Connection connection, String name, String email, String role, String passWord) throws SQLException;
    
    void addUser(String name, String email, String role, String passWord) throws  SQLException;
    
    boolean login(String email, String password) throws SQLException;
    
    void modifyName(Integer id, String name);
    
    void modifyPassword(Integer id, String password);
    
    void modifyMail(Integer id, String mail);
}
