package ru.anvarzhonov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.anvarzhonov.dto.UserDTO;
import ru.anvarzhonov.models.User;
import ru.anvarzhonov.service.UserService;

import java.security.Principal;
import java.util.Objects;

/**
 * todo Document type UserController
 */
@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model ) {

        model.addAttribute("users", userService.getAll());

        return "userList";
    }

    @GetMapping("/new")
    public String newUser(Model model) {

        model.addAttribute("user", new UserDTO());

        return "user";
    }

    @PostMapping("/new")
    public String saveUser(UserDTO userDTO, Model model) {
        if (userService.save(userDTO)) {
            return "redirect:/users";
        } else {
            model.addAttribute("user", userDTO);
            return "user";
        }
    }

    /**
     * Princial - это авторизованный пользователь с точки зрения Security
     */
    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("You're not authorize");
        }
        User user = userService.findByName(principal.getName());


        UserDTO userDTO = UserDTO.builder()
            .username(user.getName())
            .email(user.getEmail())
            .build();
        model.addAttribute("user", userDTO);

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(UserDTO userDTO, Model model, Principal principal) {
        if (principal == null || !Objects.equals(principal.getName(), userDTO.getUsername())) {
            throw new RuntimeException("You're not authorize");
        }
        if (userDTO.getPassword() != null
            && !userDTO.getPassword().isEmpty()
            && !Objects.equals(userDTO.getPassword(),userDTO.getMatchingPassword())) {
            model.addAttribute("user", userDTO);

            return "profile";
        }
        userService.updateProfile(userDTO);
        return "redirect:/users/profile";
    }
}
