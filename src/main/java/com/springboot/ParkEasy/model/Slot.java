package com.springboot.ParkEasy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "slots")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Shop number this slot belongs to
    @Column(nullable = false)
    private int shopNo;

    // Slot occupancy status
    @Column(nullable = false)
    private boolean occupied;

    /* ============================
       Getters and Setters
       ============================ */

    public Long getId() {
        return id;
    }

    public int getShopNo() {
        return shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
