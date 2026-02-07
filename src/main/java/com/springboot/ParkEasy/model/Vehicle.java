package com.springboot.ParkEasy.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique vehicle number
    @Column(nullable = false)
    private String vehicleNo;

    // Shop the customer intends to visit
    @Column(nullable = false)
    private int shopNo;

    // User-estimated parking duration
    @Column(nullable = false)
    private int estimatedHours;

    // Entry timestamp
    @Column(nullable = false)
    private LocalDateTime entryTime;

    // Exit timestamp (null = vehicle inside)
    private LocalDateTime exitTime;

    // Allocated parking slot
    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;

    /* ============================
       Getters and Setters
       ============================ */

    public Long getId() {
        return id;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getShopNo() {
        return shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
