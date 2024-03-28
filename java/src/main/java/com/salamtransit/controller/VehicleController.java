package com.salamtransit.controller;

import com.salamtransit.model.Vehicle;
import com.salamtransit.dao.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleDao vehicleDao;

    @Autowired
    public VehicleController(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle newVehicle = vehicleDao.addVehicle(vehicle);
        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long vehicleId) {
        Vehicle vehicle = vehicleDao.getVehicleById(vehicleId);
        return vehicle != null ? ResponseEntity.ok(vehicle) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        return !vehicles.isEmpty() ? ResponseEntity.ok(vehicles) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(@PathVariable("id") Long vehicleId, @RequestBody Vehicle vehicle) {
        vehicle.setVehicleId(vehicleId);
        vehicleDao.updateVehicle(vehicle);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("id") Long vehicleId) {
        vehicleDao.deleteVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }
}
