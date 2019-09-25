package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SimpleUserService implements UserService {
    
    private final UserDao userDao;
    
    
    public SimpleUserService(UserDao userDao){
        this.userDao = userDao;
        
    }
    
    public List<User> getUsers(Connection connection) throws SQLException {
        return userDao.getUsersList(connection);
    }
    
    public User getUser(String mail) throws SQLException {
        for(User user : userDao.findUsers()){
            if(user.getEmail().equals(mail)){
                return user;
            }
        }
        return null;
        
    }
    
    public String notValidated(Connection connection, String name, String email, String role, String passWord) throws SQLException {
        String validated = "newUser";
        
        for (User user : getUsers(connection)) {
            if ( user.getEmail().equals(email)) {
                validated = "registered";
            } else if (name == null || email == null || passWord == null ) {
                validated = "empty";
            }
        }
        return validated;
    }
    
    public void addUser(String name, String email, String role, String passwWord) throws  SQLException {
        userDao.addUser(name, email, role, passwWord);
    }
    
    public boolean login(String email, String password) throws SQLException {
        for(User user : userDao.findUsers()){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    
    public void modifyName(Integer id, String name) {
        try{
            userDao.updateName(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void modifyPassword(Integer id, String password) {
        try{
            userDao.updatePassword(id, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void modifyMail(Integer id, String mail) {
        try{
            userDao.updateMail(id, mail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
