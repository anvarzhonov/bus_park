package ru.anvarzhonov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anvarzhonov.models.Bus;
import ru.anvarzhonov.models.BusFlight;
import ru.anvarzhonov.repository.FlightRepo;

/**
 * todo Document type BusFlightController
 */
@Controller
@RequestMapping("flight")
public class BusFlightController {
    private final FlightRepo flightRepo;

    public BusFlightController(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("flights", flightRepo.findAll());
        return "flight_views/flight_list";
    }

    @GetMapping("/add")
    public String newDriver(Model model) {
        model.addAttribute("flight", new BusFlight());
        return "flight_views/flight_form";
    }

    @PostMapping("/add")
    public String updateDriver(@ModelAttribute BusFlight flight, Model model) {
        flightRepo.save(flight);
        model.addAttribute("drivers", flightRepo.findAll());

        return "flight_views/flight_list";
    }

    @GetMapping("/edit/{id}")
    public String editDriver(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("flight", flightRepo.findById(id).orElse(null));
        return "flight_views/flight_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable("id") Long id, Model model) {
        flightRepo.deleteById(id);
        model.addAttribute("flights", flightRepo.findAll());

        return "flight_views/flight_list";
    }
}
