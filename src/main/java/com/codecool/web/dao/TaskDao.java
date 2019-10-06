package com.codecool.web.dao;

import com.codecool.web.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao {
    Task add(Task addedTask) throws SQLException;
    
    Task getById(int taskId) throws SQLException;
    
    List<Task> getByUserId(int userId) throws SQLException;
    
    List<Task> getAll() throws SQLException;
    
    void update(int taskId, int userId, String title, String content) throws SQLException;
    
    void delete(int taskId) throws SQLException;
}
