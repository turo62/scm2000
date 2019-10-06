package com.codecool.web.service;

import com.codecool.web.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {
    Task addTask(Task newTask) throws SQLException;
    
    Task getTaskById(int id) throws SQLException;
    
    List<Task> listTasksByUserId(int userId) throws SQLException;
    
    List<Task> ListAllTasks() throws SQLException;
    
    void updateTask(int taskId, int userId, String title, String content) throws SQLException;
    
    void deleteTask(int id) throws SQLException;
}
