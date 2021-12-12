package ru.anvarzhonov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.anvarzhonov.models.Role;
import ru.anvarzhonov.models.User;
import ru.anvarzhonov.repository.UserRepo;

import java.util.Collections;

/**
 * todo Document type RegController
 */
@Controller
public class RegController {
    @Autowired
    private UserRepo userRepo;


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findFirstByName(user.getName());

        if (userFromDb != null) {
            model.addAttribute("message", "Ошибка! Пользователь с данным именем уже существует!");
            return "registration";
        }

        user.setRole(Role.USER);
        userRepo.save(user);

        return "redirect:/login";
    }
}
