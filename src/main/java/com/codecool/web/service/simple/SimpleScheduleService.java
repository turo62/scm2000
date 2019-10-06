package com.codecool.web.service.simple;

import com.codecool.web.dao.ScheduleDao;
import com.codecool.web.model.Schedule;
import com.codecool.web.service.ScheduleService;

import java.sql.SQLException;
import java.util.List;

public class SimpleScheduleService implements ScheduleService {
    private ScheduleDao scheduleDao;
    
    @Override
    public List<Schedule> listAllSchedules() throws SQLException {
        return scheduleDao.listAll();
    }
    
    @Override
    public Schedule findScheduleById(int scheduleId) throws SQLException {
        return scheduleDao.findById(scheduleId);
    }
    
    @Override
    public List<Schedule> ListSchedulesByUserId(int userId) throws SQLException {
        return scheduleDao.allByUserId(userId);
    }
    
    @Override
    public Schedule addSchedule(Schedule newSchedule) throws SQLException {
        return scheduleDao.add(newSchedule);
    }
    
    @Override
    public void updateSchedule(int id, String title, int noDays, boolean isPublished) {
        try {
            scheduleDao.update(id, title, noDays, isPublished);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteSchedule(int id) {
        try {
            scheduleDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void publishSchedule(int id) {
        try {
            scheduleDao.publish(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Schedule> listPublishedSchedules() throws SQLException {
        return scheduleDao.listPublished();
    }
}
