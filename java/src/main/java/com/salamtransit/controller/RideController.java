package com.salamtransit.controller;

import com.salamtransit.dao.RideDao;
import com.salamtransit.model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideDao rideDao;

    @Autowired
    public RideController(RideDao rideDao) {
        this.rideDao = rideDao;
    }

    @PostMapping
    public ResponseEntity<Ride> addRide(@RequestBody Ride ride) {
        rideDao.addRide(ride);
        return new ResponseEntity<>(ride, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable("id") Long rideId) {
        Optional<Ride> ride = rideDao.getRideById(rideId);
        return ride.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Ride>> getAllRides() {
        List<Ride> rides = rideDao.getAllRides();
        if (rides.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ride> updateRide(@PathVariable("id") Long id, @RequestBody Ride ride) {
        Optional<Ride> existingRide = rideDao.getRideById(id);
        if (existingRide.isPresent()) {
            // Ensure the ride ID matches the path variable id
            ride.setRideId(id);
            rideDao.updateRide(ride);
            return new ResponseEntity<>(ride, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable("id") Long id) {
        Optional<Ride> existingRide = rideDao.getRideById(id);
        if (existingRide.isPresent()) {
            rideDao.deleteRide(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
