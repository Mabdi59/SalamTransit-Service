package com.salamtransit.controller;

import com.salamtransit.model.Driver;
import com.salamtransit.dao.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverDao driverDao;

    @Autowired
    public DriverController(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @PostMapping
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        Driver newDriver = driverDao.addDriver(driver);
        return ResponseEntity.ok(newDriver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable("id") int driverId) {
        Driver driver = driverDao.getDriverById(driverId);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllDrivers() {
        List<Driver> drivers = driverDao.getAllDrivers();
        if (drivers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(drivers);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDriver(@PathVariable("id") int driverId, @RequestBody Driver driver) {
        driver.setDriverId(driverId);
        if (driverDao.updateDriver(driver)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable("id") int driverId) {
        boolean deleted = driverDao.deleteDriver(driverId);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
