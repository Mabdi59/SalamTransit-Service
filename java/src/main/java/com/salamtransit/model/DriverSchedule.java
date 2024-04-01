package com.salamtransit.model;

import java.sql.Timestamp;

public class DriverSchedule {
    private Long scheduleId;
    private Integer driverId;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private String status;

    // Constructors
    public DriverSchedule() {
    }

    public DriverSchedule(Long scheduleId, Integer driverId, Timestamp startDateTime, Timestamp endDateTime, String status) {
        this.scheduleId = scheduleId;
        this.driverId = driverId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.status = status;
    }

    // Getters and setters

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
