package com.codecool.web.dao.database;

import com.codecool.web.dao.ScheduleDao;
import com.codecool.web.model.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBScheduleDao extends AbstractDao implements ScheduleDao {
    
    public DBScheduleDao(Connection connection) {
        super(connection);
    }
    
    public List<Schedule> listAll() throws SQLException {
        List<Schedule> tempSchList = new ArrayList<>();
        
        String sql = "SELECT * FROM schedules ORDER BY schedule_id";
        
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                tempSchList.add(fetchSchedule(rs));
            }
        }
        
        return tempSchList;
    }
    
    public Schedule findById(int schId) throws SQLException {
        String sql = "SELECT * FROM schedules WHERE schedule_id = " + schId;
        
        try(Statement statement = connection.createStatement()) {
            statement.executeQuery(sql);
            ResultSet rs = statement.executeQuery(sql);
    
            while (rs.next()) {
                return new Schedule(rs.getInt("schedule_id"), rs.getInt("user_id"), rs.getString("schedule_title"), rs.getInt("day_number"), rs.getBoolean("is_published"));
            }
        
        }
        
        return null;
    }
    
    public List<Schedule> allByUserId(int userId) throws SQLException {
        List<Schedule> tempList = new ArrayList<>();
    
        String sql = "SELECT * FROM schedules WHERE user_id" + userId;
    
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                tempList.add(fetchSchedule(rs));
            }
        }
        
        return tempList;
    
    }
    
    public Schedule addSchedule(Schedule addedSch) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        
        int usId = addedSch.getUserId();
        String title = addedSch.getTitle();
        int daysNo = addedSch.getNoDays();
        boolean isPublished = addedSch.isPublished();
        
        String sql = "INSERT INTO schedules (user_id, schedule_title, day_number, is_published) VALUES (?, ?, ?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, usId);
            statement.setString(2, title);
            statement.setInt(3, daysNo);
            statement.setBoolean(4, isPublished);
            
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            
            return new Schedule(id, usId, title, daysNo, isPublished);
            
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    public void updateSchedule(int id, String title, int noDays, boolean isPublished) throws SQLException {
        String newTitle;
        int newNoDays;
        boolean newPublish;
        
        Schedule actSch = findById(id);
        Schedule newSch = new Schedule(id, actSch.getUserId(), title, noDays, isPublished);
        
        if (actSch.getTitle().equals(newSch.getTitle())) {
            newTitle = actSch.getTitle();
        } else {
            newTitle = title;
        }
        
        if (actSch.getNoDays() == newSch.getNoDays()) {
            newNoDays = actSch.getNoDays();
        } else {
            newNoDays = noDays;
        }
        
        if (actSch.isPublished() == newSch.isPublished()) {
            newPublish = actSch.isPublished();
        } else {
            newPublish = isPublished;
        }
    
    
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
    
        String sql = "UPDATE schedules SET schedule_title=?, day_number=?, is_published=? WHERE schedule_id=?";
    
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, newTitle);
            statement.setInt(2, newNoDays);
            statement.setBoolean(3, newPublish);
            statement.setInt(4, id);
            
            statement.executeUpdate();
            connection.commit();
            
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
    
    public void deleteSchedule(int schId) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        
        String sql = "DELETE FROM schedules CASCADE WHERE schedule_id = " + schId;
    
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
    
    public List<Schedule> listPublished() throws SQLException {
        List<Schedule> pubishedList = new ArrayList<>();
        
        String sql = "SELECT * FROM schedules WHERE is_published=true";
    
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                pubishedList.add(fetchSchedule(rs));
            }
        }
    
        return pubishedList;
    }
    
    public Schedule fetchSchedule(ResultSet rs) throws SQLException {
        int schId = rs.getInt("schedule_id");
        int usId = rs.getInt("user_id");
        String title = rs.getString("schedule_title");
        int noDays = rs.getInt("day_number");
        boolean isPublished = rs.getBoolean("is_published");
    
        return new Schedule(schId, usId, title, noDays, isPublished);
    }
}
