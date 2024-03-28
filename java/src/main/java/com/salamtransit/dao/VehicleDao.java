package com.salamtransit.dao;

import com.salamtransit.model.Vehicle;
import java.util.List;

public interface VehicleDao {
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Long vehicleId);
    List<Vehicle> getAllVehicles();
    void updateVehicle(Vehicle vehicle);
    void deleteVehicle(Long vehicleId);
}
