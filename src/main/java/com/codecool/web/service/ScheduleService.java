package com.codecool.web.service;

import com.codecool.web.model.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleService {
    
    List<Schedule> listAllSchedules() throws SQLException;
    
    Schedule findScheduleById(int scheduleId) throws SQLException;
    
    List<Schedule> ListSchedulesByUserId(int userId) throws SQLException;
    
    Schedule addSchedule(Schedule newSchedule) throws SQLException;
    
    void updateSchedule(int id, String title, int noDays, boolean isPublished);
    
    void deleteSchedule(int id);
    
    void publishSchedule(int id);
    
    List<Schedule> listPublishedSchedules() throws SQLException;
}
