package com.codecool.web.dao;

import com.codecool.web.model.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ScheduleDao {
    
    public List<Schedule> listAll() throws SQLException;
    
    public Schedule findById(int schId) throws SQLException;
    
    public List<Schedule> allByUserId(int userId) throws SQLException;
    
    public Schedule addSchedule(Schedule addedSch) throws SQLException;
    
    public void updateSchedule(int id, String title, int noDays, boolean isPublished) throws SQLException;
    
    public void deleteSchedule(int schId) throws SQLException;
    
    public List<Schedule> listPublished() throws SQLException;
    
    public Schedule fetchSchedule(ResultSet rs) throws SQLException;
}
