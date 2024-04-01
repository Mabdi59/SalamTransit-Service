package com.salamtransit.dao;

import com.salamtransit.model.Ride;
import java.util.List;
import java.util.Optional;

public interface RideDao {
    void addRide(Ride ride);
    Optional<Ride> getRideById(Long rideId);
    List<Ride> getAllRides();
    void updateRide(Ride ride);
    void deleteRide(Long rideId);
}
