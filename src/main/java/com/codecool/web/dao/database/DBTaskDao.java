package com.codecool.web.dao.database;

import com.codecool.web.dao.TaskDao;
import com.codecool.web.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTaskDao extends AbstractDao implements TaskDao {
    
    DBTaskDao(Connection connection) {
        super(connection);
    }
    
    
    @Override
    public Task add(Task addedTask) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
    
        int taskId = addedTask.getTaskId();
        int usId = addedTask.getUserId();
        String title = addedTask.getTaskTitle();
        String content = addedTask.getTaskContent();
    
        String sql = "INSERT INTO tasks (user_id, title, content) VALUES (?, ?, ?)";
    
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, usId);
            statement.setString(2, title);
            statement.setString(2, content);
        
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
        
            return new Task(id, usId, title, content);
        
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    @Override
    public Task getById(int taskId) throws SQLException {
        Task myTask = null;
        String sql = "SELECT * FROM Tasks WHERE Task_taskId = " + taskId;
    
        try(Statement statement = connection.createStatement()) {
            statement.executeQuery(sql);
            ResultSet rs = statement.executeQuery(sql);
        
            
            myTask = new  Task(rs.getInt("task_id"), rs.getInt("user_taskId"), rs.getString("title"), rs.getString("content"));
        
        }
        
        return myTask;
        
    }
    
    @Override
    public List<Task> getByUserId(int userId) throws SQLException {
        List<Task> tempList = new ArrayList<>();
    
        String sql = "SELECT * FROM tasks WHERE user_taskId" + userId;
    
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                tempList.add(fetchTask(rs));
            }
        }
    
        return tempList;
    }
    
    @Override
    public List<Task> getAll() throws SQLException {
        List<Task> tempTaskList = new ArrayList<>();
    
        String sql = "SELECT * FROM rasks ORDER BY task_id";
    
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                tempTaskList.add(fetchTask(rs));
            }
        }
    
        return tempTaskList;
    }
    
    @Override
    public void update(int taskId, int userId, String title, String content) throws SQLException {
        String newTitle;
        String newContent;
    
        Task actTask = getById(taskId);
        Task newTask = new Task(taskId, actTask.getUserId(), title, content);
    
        if (actTask.getTaskTitle().equals(newTask.getTaskTitle())) {
            newTitle = actTask.getTaskTitle();
        } else {
            newTitle = title;
        }
    
        if (actTask.getTaskContent().equals(newTask.getTaskContent())) {
            newContent = actTask.getTaskContent();
        } else {
            newContent = content;
        }
    
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
    
        String sql = "UPDATE tasks SET title=?, content=? WHERE task_id=?";
    
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
        
            statement.setString(1, newTitle);
            statement.setString(1, newContent);
            
        
            statement.executeUpdate();
            connection.commit();
        
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    @Override
    public void delete(int taskId) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
    
        String sql = "DELETE FROM tasks CASCADE WHERE task_id = " + taskId;
    
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            connection.commit();
        
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    private Task fetchTask(ResultSet rs) throws SQLException {
        int taskId = rs.getInt("task_idd");
        int usId = rs.getInt("user_id");
        String title = rs.getString("title");
        String content = rs.getString("content");
        
        return new Task(taskId, usId, title, content);
    }
}
