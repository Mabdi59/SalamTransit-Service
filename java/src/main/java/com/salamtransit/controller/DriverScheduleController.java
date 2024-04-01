package com.salamtransit.controller;

import com.salamtransit.dao.DriverScheduleDao;
import com.salamtransit.model.DriverSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver-schedules")
public class DriverScheduleController {

    private final DriverScheduleDao driverScheduleDao;

    @Autowired
    public DriverScheduleController(DriverScheduleDao driverScheduleDao) {
        this.driverScheduleDao = driverScheduleDao;
    }

    @PostMapping
    public ResponseEntity<DriverSchedule> addDriverSchedule(@RequestBody DriverSchedule driverSchedule) {
        driverScheduleDao.addDriverSchedule(driverSchedule);
        return new ResponseEntity<>(driverSchedule, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<DriverSchedule> getDriverScheduleById(@PathVariable Long scheduleId) {
        DriverSchedule driverSchedule = driverScheduleDao.getDriverScheduleById(scheduleId);
        if (driverSchedule != null) {
            return new ResponseEntity<>(driverSchedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DriverSchedule>> getAllDriverSchedules() {
        List<DriverSchedule> schedules = driverScheduleDao.getAllDriverSchedules();
        if (!schedules.isEmpty()) {
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<DriverSchedule> updateDriverSchedule(@PathVariable Long scheduleId, @RequestBody DriverSchedule driverSchedule) {
        DriverSchedule existingSchedule = driverScheduleDao.getDriverScheduleById(scheduleId);
        if (existingSchedule != null) {
            driverSchedule.setScheduleId(scheduleId); // Ensure ID alignment
            driverScheduleDao.updateDriverSchedule(driverSchedule);
            return new ResponseEntity<>(driverSchedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteDriverSchedule(@PathVariable Long scheduleId) {
        DriverSchedule existingSchedule = driverScheduleDao.getDriverScheduleById(scheduleId);
        if (existingSchedule != null) {
            driverScheduleDao.deleteDriverSchedule(scheduleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
