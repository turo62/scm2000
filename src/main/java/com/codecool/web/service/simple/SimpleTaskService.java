package com.codecool.web.service.simple;

import com.codecool.web.dao.TaskDao;
import com.codecool.web.model.Task;
import com.codecool.web.service.TaskService;

import java.sql.SQLException;
import java.util.List;

public class SimpleTaskService implements TaskService {
    
    private TaskDao taskDao;
    
    public SimpleTaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
    
    @Override
    public Task addTask(Task newTask) throws SQLException {
        return taskDao.add(newTask);
    }
    
    @Override
    public Task getTaskById(int id) throws SQLException {
        return taskDao.getById(id);
    }
    
    @Override
    public List<Task> listTasksByUserId(int userId) throws SQLException {
        return taskDao.getByUserId(userId);
    }
    
    @Override
    public List<Task> ListAllTasks() throws SQLException {
        return taskDao.getAll();
    }
    
    @Override
    public void updateTask(int taskId, int userId, String title, String content) throws SQLException {
        taskDao.update(taskId, userId, title, content);
    }
    
    @Override
    public void deleteTask(int id) throws SQLException {
        taskDao.delete(id);
    }
}
