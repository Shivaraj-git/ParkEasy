//package com.springboot.ParkEasy.config;
//
//import com.springboot.ParkEasy.model.Slot;
//import com.springboot.ParkEasy.model.Vehicle;
//import com.springboot.ParkEasy.repository.SlotRepository;
//import com.springboot.ParkEasy.repository.VehicleRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public class DataLoader {
//
//    private final SlotRepository slotRepo;
//    private final VehicleRepository vehicleRepo;
//
//    public DataLoader(SlotRepository slotRepo, VehicleRepository vehicleRepo) {
//        this.slotRepo = slotRepo;
//        this.vehicleRepo = vehicleRepo;
//    }
//
//    @PostConstruct
//    public void loadInitialData() {
//
//        // 1️⃣ Create Slots (5 shops × 5 slots)
//        if (slotRepo.count() == 0) {
//            for (int shopNo = 1; shopNo <= 5; shopNo++) {
//                for (int i = 1; i <= 5; i++) {
//                    Slot slot = new Slot();
//                    slot.setShopNo(shopNo);
//                    slot.setOccupied(false);
//                    slotRepo.save(slot);
//                }
//            }
//        }
//
//        // 2️⃣ Create Demo Vehicles (optional)
//        if (vehicleRepo.count() == 0) {
//
//            Slot s1 = slotRepo.findByShopNoAndOccupiedFalse(1).get(0);
//            s1.setOccupied(true);
//            slotRepo.save(s1);
//
//            Vehicle v1 = new Vehicle();
//            v1.setVehicleNo("TN01AB1234");
//            v1.setShopNo(1);
//            v1.setEstimatedHours(2);
//            v1.setEntryTime(LocalDateTime.now().minusHours(1));
//            v1.setSlot(s1);
//            vehicleRepo.save(v1);
//
//            Slot s2 = slotRepo.findByShopNoAndOccupiedFalse(2).get(0);
//            s2.setOccupied(true);
//            slotRepo.save(s2);
//
//            Vehicle v2 = new Vehicle();
//            v2.setVehicleNo("TN02CD5678");
//            v2.setShopNo(2);
//            v2.setEstimatedHours(3);
//            v2.setEntryTime(LocalDateTime.now().minusHours(2));
//            v2.setSlot(s2);
//            vehicleRepo.save(v2);
//        }
//    }
//}
