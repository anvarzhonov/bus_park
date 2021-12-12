package ru.anvarzhonov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anvarzhonov.models.Bus;
import ru.anvarzhonov.models.BusFlight;
import ru.anvarzhonov.models.InsuranceCompany;
import ru.anvarzhonov.repository.FlightRepo;
import ru.anvarzhonov.repository.InsuranceRepo;

/**
 * todo Document type InsuranceCompController
 */
@Controller
@RequestMapping("insurance")
public class InsuranceCompController {
    private final InsuranceRepo insuranceRepo;

    public InsuranceCompController(InsuranceRepo insuranceRepo) {
        this.insuranceRepo = insuranceRepo;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("companies", insuranceRepo.findAll());
        return "company_views/company_list";
    }

    @GetMapping("/add")
    public String newDriver(Model model) {
        model.addAttribute("company", new InsuranceCompany());
        return "company_views/company_form";
    }

    @PostMapping("/add")
    public String updateDriver(@ModelAttribute InsuranceCompany company, Model model) {
        insuranceRepo.save(company);
        model.addAttribute("companies", insuranceRepo.findAll());

        return "company_views/company_list";
    }

    @GetMapping("/edit/{id}")
    public String editDriver(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("company", insuranceRepo.findById(id).orElse(null));
        return "company_views/company_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable("id") Long id, Model model) {
        insuranceRepo.deleteById(id);
        model.addAttribute("companies", insuranceRepo.findAll());

        return "company_views/company_list";
    }
}
