package com.springboot.ParkEasy.service;

import com.springboot.ParkEasy.model.Slot;
import com.springboot.ParkEasy.model.Vehicle;
import com.springboot.ParkEasy.repository.SlotRepository;
import com.springboot.ParkEasy.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    private final VehicleRepository vehicleRepo;
    private final SlotRepository slotRepo;

    // Configurable mall size
    private static final int TOTAL_SHOPS = 10;

    public ParkingService(VehicleRepository vehicleRepo, SlotRepository slotRepo) {
        this.vehicleRepo = vehicleRepo;
        this.slotRepo = slotRepo;
    }

    /* ============================
       SLOT ALLOCATION STRATEGY
       ============================ */
    public boolean shopExists(int shopNo) {
        return slotRepo.existsByShopNo(shopNo);
    }

    public Slot allocateSlot(int shopNo, int estHours) {

        // Expand search outward from requested shop
        for (int distance = 0; distance < TOTAL_SHOPS; distance++) {

            int leftShop = shopNo - distance;
            int rightShop = shopNo + distance;

            // Check left side
            if (leftShop >= 1) {
                Slot slot = findFreeSlot(leftShop, estHours);
                if (slot != null) return slot;
            }

            // Check right side (avoid duplicate when distance = 0)
            if (rightShop <= TOTAL_SHOPS && rightShop != leftShop) {
                Slot slot = findFreeSlot(rightShop, estHours);
                if (slot != null) return slot;
            }
        }
        return null; // Entire parking full
    }

    private Slot findFreeSlot(int shopNo, int estHours) {
        List<Slot> freeSlots = slotRepo.findByShopNoAndOccupiedFalse(shopNo);
        if (freeSlots.isEmpty()) return null;

        Slot chosenSlot = chooseSlotBasedOnStay(freeSlots, estHours);
        chosenSlot.setOccupied(true);
        return slotRepo.save(chosenSlot);
    }

    private Slot chooseSlotBasedOnStay(List<Slot> freeSlots, int estHours) {
        // Long stay → far slot, Short stay → near slot
        return estHours > 2
                ? freeSlots.get(freeSlots.size() - 1)
                : freeSlots.get(0);
    }

    /* ============================
       VEHICLE ENTRY
       ============================ */

    public Vehicle entry(String vehicleNo, int shopNo, int estHours) {

        // Check if shop is configured
        if (!shopExists(shopNo)) {
            return null; // special case: shop not found
        }

        Vehicle activeVehicle =
                vehicleRepo.findByVehicleNoAndExitTimeIsNull(vehicleNo);

        if (activeVehicle != null) {
            return activeVehicle;
        }

        Slot allocatedSlot = allocateSlot(shopNo, estHours);
        if (allocatedSlot == null) {
            return null; // parking full
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNo(vehicleNo);
        vehicle.setShopNo(shopNo);
        vehicle.setEstimatedHours(estHours);
        vehicle.setEntryTime(LocalDateTime.now());
        vehicle.setSlot(allocatedSlot);

        return vehicleRepo.save(vehicle);
    }


    /* ============================
       VEHICLE EXIT + BILLING
       ============================ */

    public int exit(String vehicleNo) {

        Vehicle vehicle =
                vehicleRepo.findByVehicleNoAndExitTimeIsNull(vehicleNo);

        if (vehicle == null) {
            return -1; // Vehicle not found
        }

        vehicle.setExitTime(LocalDateTime.now());
        vehicleRepo.save(vehicle);

        // Free slot
        Slot slot = vehicle.getSlot();
        if (slot != null) {
            slot.setOccupied(false);
            slotRepo.save(slot);
        }

        return calculateBill(vehicle);
    }

    private int calculateBill(Vehicle vehicle) {

        long totalHours =
                Duration.between(vehicle.getEntryTime(),
                        vehicle.getExitTime()).toHours() + 1;

        long estimated = vehicle.getEstimatedHours();

        if (totalHours <= estimated) {
            return (int) (totalHours * 30);
        }

        long extraHours = totalHours - estimated;
        return (int) (estimated * 30 + extraHours * 50);
    }

    /* ============================
       UTILITY
       ============================ */

    public boolean isParkingFull(int shopNo) {
        return slotRepo.findByShopNoAndOccupiedFalse(shopNo).isEmpty();
    }
}
