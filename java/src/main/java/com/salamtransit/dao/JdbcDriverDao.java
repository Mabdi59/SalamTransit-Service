package com.salamtransit.dao;

import com.salamtransit.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JdbcDriverDao implements DriverDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcDriverDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Driver> rowMapper = (rs, rowNum) -> new Driver(
            rs.getInt("driver_id"),
            rs.getInt("user_id"),
            rs.getString("license_number"),
            rs.getObject("vehicle_id") != null ? rs.getInt("vehicle_id") : null,
            rs.getString("status"),
            rs.getTimestamp("join_date")
    );

    @Override
    public Driver addDriver(Driver driver) {
        String sql = "INSERT INTO drivers (user_id, license_number, vehicle_id, status, join_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, driver.getUserId(), driver.getLicenseNumber(), driver.getVehicleId(), driver.getStatus(), driver.getJoinDate());
        return driver;
    }

    @Override
    public Driver getDriverById(int driverId) {
        try {
            String sql = "SELECT * FROM drivers WHERE driver_id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{driverId}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        String sql = "SELECT * FROM drivers";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public boolean updateDriver(Driver driver) {
        String sql = "UPDATE drivers SET user_id = ?, license_number = ?, vehicle_id = ?, status = ?, join_date = ? WHERE driver_id = ?";
        int update = jdbcTemplate.update(sql, driver.getUserId(), driver.getLicenseNumber(), driver.getVehicleId(), driver.getStatus(), driver.getJoinDate(), driver.getDriverId());
        return update > 0;
    }

    @Override
    public boolean deleteDriver(int driverId) {
        String sql = "DELETE FROM drivers WHERE driver_id = ?";
        int update = jdbcTemplate.update(sql, driverId);
        return update > 0;
    }
}
