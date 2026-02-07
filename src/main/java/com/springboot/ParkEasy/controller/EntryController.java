package com.springboot.ParkEasy.controller;

import com.springboot.ParkEasy.model.Vehicle;
import com.springboot.ParkEasy.service.ParkingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EntryController {

    private final ParkingService parkingService;

    public EntryController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    // Show entry page
    @GetMapping("/entry")
    public String showEntryForm() {
        return "entry"; // entry.jsp
    }

    // Handle vehicle entry
    @PostMapping("/entry")
    public String processEntry(@RequestParam String vehicleNo,
                               @RequestParam int shopNo,
                               @RequestParam int estHours,
                               Model model) {

        Vehicle vehicle = parkingService.entry(vehicleNo, shopNo, estHours);

        if (vehicle == null) {
            if (!parkingService.shopExists(shopNo)) {
                model.addAttribute(
                        "message",
                        "Slots not configured for the Shop."
                );
            } else {
                model.addAttribute(
                        "message",
                        "Parking Full for all nearby shops!"
                );
            }

        } else {
            model.addAttribute(
                    "message",
                    "Slot Allocated Successfully: " +
                            vehicle.getSlot().getId()
            );
        }

        return "entry";
    }

}
