package com.salamtransit.dao;

import com.salamtransit.model.Ride;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcRideDao implements RideDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcRideDao.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRideDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Ride> rideRowMapper = (rs, rowNum) -> {
        Ride ride = new Ride();
        ride.setRideId(rs.getLong("ride_id"));
        ride.setUserId(rs.getInt("user_id"));
        ride.setDriverId(rs.getInt("driver_id"));
        ride.setVehicleId(rs.getInt("vehicle_id"));
        ride.setPickupLocation(rs.getString("pickup_location"));
        ride.setDropoffLocation(rs.getString("dropoff_location"));
        ride.setScheduledPickupTime(rs.getTimestamp("scheduled_pickup_time"));
        ride.setActualPickupTime(rs.getTimestamp("actual_pickup_time"));
        ride.setDropoffTime(rs.getTimestamp("dropoff_time"));
        ride.setStatus(rs.getString("status"));
        ride.setFareEstimate(rs.getBigDecimal("fare_estimate"));
        ride.setActualFare(rs.getBigDecimal("actual_fare"));
        return ride;
    };

    @Override
    public void addRide(Ride ride) {
        String sql = "INSERT INTO rides (user_id, driver_id, vehicle_id, pickup_location, dropoff_location, scheduled_pickup_time, actual_pickup_time, dropoff_time, status, fare_estimate, actual_fare) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, ride.getUserId(), ride.getDriverId(), ride.getVehicleId(), ride.getPickupLocation(), ride.getDropoffLocation(), ride.getScheduledPickupTime(), ride.getActualPickupTime(), ride.getDropoffTime(), ride.getStatus(), ride.getFareEstimate(), ride.getActualFare());
        } catch (DataAccessException e) {
            log.error("Error adding ride", e);
            throw e;
        }
    }

    @Override
    public Optional<Ride> getRideById(Long rideId) {
        String sql = "SELECT * FROM rides WHERE ride_id = ?";
        try {
            Ride ride = jdbcTemplate.queryForObject(sql, new Object[]{rideId}, rideRowMapper);
            return Optional.ofNullable(ride);
        } catch (DataAccessException e) {
            log.error("Error finding ride with ID: " + rideId, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Ride> getAllRides() {
        String sql = "SELECT * FROM rides";
        try {
            return jdbcTemplate.query(sql, rideRowMapper);
        } catch (DataAccessException e) {
            log.error("Error fetching all rides", e);
            throw e;
        }
    }

    @Override
    public void updateRide(Ride ride) {
        String sql = "UPDATE rides SET user_id = ?, driver_id = ?, vehicle_id = ?, pickup_location = ?, dropoff_location = ?, scheduled_pickup_time = ?, actual_pickup_time = ?, dropoff_time = ?, status = ?, fare_estimate = ?, actual_fare = ? WHERE ride_id = ?";
        try {
            jdbcTemplate.update(sql, ride.getUserId(), ride.getDriverId(), ride.getVehicleId(), ride.getPickupLocation(), ride.getDropoffLocation(), ride.getScheduledPickupTime(), ride.getActualPickupTime(), ride.getDropoffTime(), ride.getStatus(), ride.getFareEstimate(), ride.getActualFare(), ride.getRideId());
        } catch (DataAccessException e) {
            log.error("Error updating ride with ID: " + ride.getRideId(), e);
            throw e;
        }
    }

    @Override
    public void deleteRide(Long rideId) {
        String sql = "DELETE FROM rides WHERE ride_id = ?";
        try {
            jdbcTemplate.update(sql, rideId);
        } catch (DataAccessException e) {
            log.error("Error deleting ride with ID: " + rideId, e);
            throw e;
        }
    }
}
