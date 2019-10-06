package com.codecool.web.dao;

import com.codecool.web.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao {
    public Task addTask(Task addedTask) throws SQLException;
    
    Task getByTaskId(int taskId);
    
    List<Task> getTasksByUserId(int userId) throws SQLException;
    
    List<Task> getAllTasks() throws SQLException;
    
    void updateTask(int taskId, int userId, String title, String content) throws SQLException;
    
    void deleteTask(int taskId) throws SQLException;
}
