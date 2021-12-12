package ru.anvarzhonov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anvarzhonov.models.Bus;
import ru.anvarzhonov.models.BusDriver;
import ru.anvarzhonov.repository.BusRepo;

/**
 * todo Document type BusController
 */

@Controller
@RequestMapping("/bus")
public class BusController {
    private final BusRepo busRepo;

    public BusController(BusRepo busRepo) {
        this.busRepo = busRepo;
    }

    @GetMapping()
    public String showAll(Model model) {
            model.addAttribute("buses", busRepo.findAll());
        return "bus_views/bus_list";
    }

    @GetMapping("/add")
    public String newDriver(Model model) {
        model.addAttribute("bus", new Bus());
        return "bus_views/bus_form";
    }

    @PostMapping("/add")
    public String updateDriver(@ModelAttribute Bus bus, Model model) {
        busRepo.save(bus);
        model.addAttribute("drivers", busRepo.findAll());

        return "bus_views/bus_list";
    }

    @GetMapping("/edit/{id}")
    public String editDriver(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("bus", busRepo.findById(id).orElse(null));
        return "bus_views/bus_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable("id") Long id, Model model) {
        busRepo.deleteById(id);
        model.addAttribute("buses", busRepo.findAll());

        return "bus_views/bus_list";
    }
}
