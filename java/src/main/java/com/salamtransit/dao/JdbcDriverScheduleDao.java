package com.salamtransit.dao;

import com.salamtransit.model.DriverSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JdbcDriverScheduleDao implements DriverScheduleDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcDriverScheduleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<DriverSchedule> rowMapper = (rs, rowNum) -> new DriverSchedule(
            rs.getLong("schedule_id"),
            rs.getInt("driver_id"),
            rs.getTimestamp("start_date_time"),
            rs.getTimestamp("end_date_time"),
            rs.getString("status")
    );

    @Override
    public void addDriverSchedule(DriverSchedule driverSchedule) {
        String sql = "INSERT INTO driver_schedules (driver_id, start_date_time, end_date_time, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, driverSchedule.getDriverId(), driverSchedule.getStartDateTime(), driverSchedule.getEndDateTime(), driverSchedule.getStatus());
    }

    @Override
    public DriverSchedule getDriverScheduleById(Long scheduleId) {
        String sql = "SELECT * FROM driver_schedules WHERE schedule_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{scheduleId}, rowMapper);
    }

    @Override
    public List<DriverSchedule> getAllDriverSchedules() {
        String sql = "SELECT * FROM driver_schedules";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void updateDriverSchedule(DriverSchedule driverSchedule) {
        String sql = "UPDATE driver_schedules SET driver_id = ?, start_date_time = ?, end_date_time = ?, status = ? WHERE schedule_id = ?";
        jdbcTemplate.update(sql, driverSchedule.getDriverId(), driverSchedule.getStartDateTime(), driverSchedule.getEndDateTime(), driverSchedule.getStatus(), driverSchedule.getScheduleId());
    }

    @Override
    public void deleteDriverSchedule(Long scheduleId) {
        String sql = "DELETE FROM driver_schedules WHERE schedule_id = ?";
        jdbcTemplate.update(sql, scheduleId);
    }
}
