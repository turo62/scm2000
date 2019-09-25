package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    
    public List<User> findUsers() throws SQLException;
    
    public void addUser(String name, String email, String role, String password) throws SQLException;
    
    public User getUser(String email, String passWord) throws SQLException;
    
    public List<User> getUsersList(Connection connection) throws SQLException;
    
    public void updateName(Integer userId, String name) throws SQLException;
    
    public void updatePassword(Integer userId, String password) throws SQLException;
    
    public void updateMail(Integer userId, String mail) throws SQLException;

}
