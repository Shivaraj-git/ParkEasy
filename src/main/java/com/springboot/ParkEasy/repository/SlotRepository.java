package com.springboot.ParkEasy.repository;

import com.springboot.ParkEasy.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    // Fetch all free slots for a given shop
    List<Slot> findByShopNoAndOccupiedFalse(int shopNo);
    boolean existsByShopNo(int shopNo);

}
