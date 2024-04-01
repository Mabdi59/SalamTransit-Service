package com.salamtransit.dao;

import com.salamtransit.model.DriverSchedule;
import java.util.List;

public interface DriverScheduleDao {
    void addDriverSchedule(DriverSchedule driverSchedule);
    DriverSchedule getDriverScheduleById(Long scheduleId);
    List<DriverSchedule> getAllDriverSchedules();
    void updateDriverSchedule(DriverSchedule driverSchedule);
    void deleteDriverSchedule(Long scheduleId);
}
