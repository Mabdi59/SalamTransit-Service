package com.salamtransit.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Ride {
    private Long rideId;
    private Integer userId;
    private Integer driverId;
    private Integer vehicleId;
    private String pickupLocation;
    private String dropoffLocation;
    private Timestamp scheduledPickupTime;
    private Timestamp actualPickupTime;
    private Timestamp dropoffTime;
    private String status;
    private BigDecimal fareEstimate;
    private BigDecimal actualFare;

    // No-argument constructor
    public Ride() {
    }
    // Constructors

    public Ride(Long rideId, Integer userId, Integer driverId, Integer vehicleId, String pickupLocation, String dropoffLocation, Timestamp scheduledPickupTime, Timestamp actualPickupTime, Timestamp dropoffTime, String status, BigDecimal fareEstimate, BigDecimal actualFare) {
        this.rideId = rideId;
        this.userId = userId;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.scheduledPickupTime = scheduledPickupTime;
        this.actualPickupTime = actualPickupTime;
        this.dropoffTime = dropoffTime;
        this.status = status;
        this.fareEstimate = fareEstimate;
        this.actualFare = actualFare;
    }

    // Getters, and Setters

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public Timestamp getScheduledPickupTime() {
        return scheduledPickupTime;
    }

    public void setScheduledPickupTime(Timestamp scheduledPickupTime) {
        this.scheduledPickupTime = scheduledPickupTime;
    }

    public Timestamp getActualPickupTime() {
        return actualPickupTime;
    }

    public void setActualPickupTime(Timestamp actualPickupTime) {
        this.actualPickupTime = actualPickupTime;
    }

    public Timestamp getDropoffTime() {
        return dropoffTime;
    }

    public void setDropoffTime(Timestamp dropoffTime) {
        this.dropoffTime = dropoffTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getFareEstimate() {
        return fareEstimate;
    }

    public void setFareEstimate(BigDecimal fareEstimate) {
        this.fareEstimate = fareEstimate;
    }

    public BigDecimal getActualFare() {
        return actualFare;
    }

    public void setActualFare(BigDecimal actualFare) {
        this.actualFare = actualFare;
    }
}
