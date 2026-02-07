package com.springboot.ParkEasy.controller;

import com.springboot.ParkEasy.model.Slot;
import com.springboot.ParkEasy.repository.SlotRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final SlotRepository slotRepo;

    public AdminController(SlotRepository slotRepo) {
        this.slotRepo = slotRepo;
    }

    // Show slot creation page
    @GetMapping("/slots")
    public String showSlotPage() {
        return "admin-slots";
    }

    // Handle slot creation
    @PostMapping("/slots")
    public String createSlots(@RequestParam int shopNo,
                              @RequestParam int slotCount,
                              Model model) {

        for (int i = 1; i <= slotCount; i++) {
            Slot slot = new Slot();
            slot.setShopNo(shopNo);
            slot.setOccupied(false);
            slotRepo.save(slot);
        }

        model.addAttribute("message",
                slotCount + " slots added for Shop " + shopNo);
        return "admin-slots";
    }
}
