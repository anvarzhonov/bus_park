package ru.anvarzhonov.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import ru.anvarzhonov.dto.UserDTO;
import ru.anvarzhonov.models.User;

import java.util.List;

public interface UserService extends UserDetailsService { // security
    boolean save(UserDTO userDTO);

    List<UserDTO> getAll();

    User findByName (String name);
    void updateProfile(UserDTO userDTO);
}
