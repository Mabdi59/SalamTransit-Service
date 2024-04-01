package com.salamtransit.dao;

import com.salamtransit.model.Driver;
import java.util.List;

public interface DriverDao {
    Driver addDriver(Driver driver);
    Driver getDriverById(int driverId);
    List<Driver> getAllDrivers();
    boolean updateDriver(Driver driver);
    boolean deleteDriver(int driverId);
}
