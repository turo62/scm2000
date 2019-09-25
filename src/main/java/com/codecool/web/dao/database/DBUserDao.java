package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.enums.Role;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DBUserDao extends AbstractDao implements UserDao {
    public DBUserDao(Connection connection) {
        super(connection);
    }
    
    public List<User> findUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        
        String sql = "SELECT user_id, user_name, email, user_role, password FROM users";
        try (Statement stmnt = connection.createStatement();
             ResultSet resultSet = stmnt.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Integer id = resultSet.getInt("user_id");
                String name = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                Role role = Role.valueOf(resultSet.getString("user_role").toUpperCase());
                String pw = resultSet.getString("password");
                users.add(new User(id, name, email, role, pw));
            }
        }
        
        return users;
    }
    
    public void addUser(String name, String email, String role, String password) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO users (user_name, email, user_role, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement prstmt = connection.prepareStatement(sql)) {
            prstmt.setString(1,name);
            prstmt.setString(2, email);
            prstmt.setString(3, role);
            prstmt.setString(4, password);
            
            executeInsert(prstmt);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    public User getUser(String email, String passWord) throws SQLException {
        User user = null;
        
        try {
            for (User user1 : findUsers()) {
                if (user1.getEmail().equals(email) && user1.getPassword().equals(passWord)) {
                    Integer id = user1.getUserId();
                    String name = user1.getName();
                    String mail = user1.getEmail();
                    Role role = user1.getRole();
                    String pw = user1.getPassword();
                    
                    return new User(id, name, mail, role, pw);
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return user;
    }
    
    public List<User> getUsersList(Connection connection) throws SQLException{
        List<User> userList = new ArrayList<>();
        String sql = "SELECT user_id, user_name, email, user_role, password from users";
        try(Statement statement = this.connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String id = String.valueOf(resultSet.getInt("user_id"));
                String name = resultSet.getString("user_name");
                String mail = resultSet.getString("email");
                Role role = Role.valueOf(resultSet.getString("user_role").toUpperCase());
                String pw = resultSet.getString("password");
                userList.add(new User(Integer.valueOf(id), name, mail, role, pw));
            }
        }
        return userList;
    }
    
    public void updateName(Integer userId, String name) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE users set user_name=? where user_id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, userId);
            executeInsert(preparedStatement);
            connection.commit();
        }catch (SQLException e){
            connection.rollback();
            throw e;
        }finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    public void updatePassword(Integer userId, String password) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE users SET password=? where user_id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, userId);
            executeInsert(preparedStatement);
            connection.commit();
        }catch (SQLException e){
            connection.rollback();
            throw e;
        }finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    public void updateMail(Integer userId, String mail) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE users SET email=? WHERE user_id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,mail);
            preparedStatement.setInt(2, userId);
            executeInsert(preparedStatement);
            connection.commit();
        }catch (SQLException e){
            connection.rollback();
            throw e;
        }finally {
            connection.setAutoCommit(autoCommit);
        }
        
    }
}
