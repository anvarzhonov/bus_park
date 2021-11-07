package ru.anvarzhonov.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anvarzhonov.models.BusDriver;
import ru.anvarzhonov.service.BusDriverService;

import java.util.List;

@Controller
@RequestMapping("/driver")
public class BusDriverController {
    private BusDriverService busDriverService;

    @Autowired
    public void setBusDriverService(BusDriverService busDriverService) {
        this.busDriverService = busDriverService;
    }

    @GetMapping()
    public String showAll(Model model) {
        List<BusDriver> drivers = busDriverService.showAll();

        model.addAttribute("drivers", drivers);


        return "driver_views/drivers_list";
    }

    @GetMapping("/add")
    public String newDriver(Model model) {
        model.addAttribute("driver", new BusDriver());
        return "driver_views/driver_form";
    }

    @PostMapping("/add")
    public String updateDriver(@ModelAttribute BusDriver busDriver, Model model) {
        busDriverService.addOrUpdate(busDriver);
        model.addAttribute("drivers", busDriverService.showAll());

        return "driver_views/drivers_list";
    }

    @GetMapping("/edit/{id}")
    public String editDriver(@PathVariable(value = "id") Long id, Model model) {
        BusDriver busDriver = busDriverService.getById(id);
        model.addAttribute("driver", busDriver);
        return "driver_views/driver_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable("id") Long id, Model model) {
        busDriverService.remove(id);
        model.addAttribute("drivers", busDriverService.showAll());

        return "driver_views/drivers_list";
    }




}
