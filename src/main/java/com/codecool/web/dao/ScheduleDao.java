package com.codecool.web.dao;

import com.codecool.web.model.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ScheduleDao {
    
    List<Schedule> listAll() throws SQLException;
    
    Schedule findById(int schId) throws SQLException;
    
    List<Schedule> allByUserId(int userId) throws SQLException;
    
    Schedule add(Schedule addedSch) throws SQLException;
    
    void update(int id, String title, int noDays, boolean isPublished) throws SQLException;
    
    void delete(int schId) throws SQLException;
    
    void publish(int schId) throws SQLException;
    
    List<Schedule> listPublished() throws SQLException;
    
    Schedule fetchSchedule(ResultSet rs) throws SQLException;
}
