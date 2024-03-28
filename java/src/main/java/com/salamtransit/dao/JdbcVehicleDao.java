package com.salamtransit.dao;

import com.salamtransit.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcVehicleDao implements VehicleDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcVehicleDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (make, model, year, license_plate, status, capacity) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getLicensePlate(), vehicle.getStatus(), vehicle.getCapacity());
        return vehicle;
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        String sql = "SELECT * FROM vehicles WHERE vehicle_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{vehicleId}, new BeanPropertyRowMapper<>(Vehicle.class));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        String sql = "SELECT * FROM vehicles";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vehicle.class));
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        String sql = "UPDATE vehicles SET make = ?, model = ?, year = ?, license_plate = ?, status = ?, capacity = ? WHERE vehicle_id = ?";
        jdbcTemplate.update(sql, vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getLicensePlate(), vehicle.getStatus(), vehicle.getCapacity(), vehicle.getVehicleId());
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        String sql = "DELETE FROM vehicles WHERE vehicle_id = ?";
        jdbcTemplate.update(sql, vehicleId);
    }
}
