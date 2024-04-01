package com.salamtransit.model;

import java.sql.Timestamp;

public class Driver {
    private int driverId;
    private int userId;
    private String licenseNumber;
    private Integer vehicleId;
    private String status;
    private Timestamp joinDate;


    public Driver() {

    }

    // Parameterized constructor
    public Driver(int driverId, int userId, String licenseNumber, Integer vehicleId, String status, Timestamp joinDate) {
        this.driverId = driverId;
        this.userId = userId;
        this.licenseNumber = licenseNumber;
        this.vehicleId = vehicleId;
        this.status = status;
        this.joinDate = joinDate;
    }

    // Getters and setters
    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }
}
