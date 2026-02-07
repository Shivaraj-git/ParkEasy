package com.springboot.ParkEasy.repository;

import com.springboot.ParkEasy.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Find active vehicle (vehicle inside parking)
    Vehicle findByVehicleNoAndExitTimeIsNull(String vehicleNo);
}
