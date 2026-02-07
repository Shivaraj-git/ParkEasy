package com.springboot.ParkEasy.controller;

import com.springboot.ParkEasy.service.ParkingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExitController {

    private final ParkingService parkingService;

    public ExitController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    // Show exit page
    @GetMapping("/exit")
    public String showExitForm() {
        return "exit"; // exit.jsp
    }

    // Handle vehicle exit
    @PostMapping("/exit")
    public String processExit(@RequestParam String vehicleNo, Model model) {

        int billAmount = parkingService.exit(vehicleNo);

        if (billAmount == -1) {
            model.addAttribute("message", "Vehicle not found or already exited!");
            return "exit";
        }

        model.addAttribute("vehicleNo", vehicleNo);
        model.addAttribute("billAmount", billAmount);

        return "bill"; // bill.jsp
    }
}
