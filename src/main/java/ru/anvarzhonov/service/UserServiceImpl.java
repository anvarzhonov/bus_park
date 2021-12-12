package ru.anvarzhonov.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.anvarzhonov.dto.UserDTO;
import ru.anvarzhonov.models.Role;
import ru.anvarzhonov.models.User;
import ru.anvarzhonov.repository.UserRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * todo Document type UserService
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new RuntimeException("Password is not equals");
        }

        User user = User.builder()
            .name(userDTO.getUsername())
            .password(passwordEncoder.encode(userDTO.getPassword()))
            .email(userDTO.getEmail())
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return true;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    private UserDTO toDto(User user) {
        return UserDTO.builder()
            .username(user.getName())
            .email(user.getEmail())
            .build();
    }

    @Override
    public User findByName(String name) {
        return userRepository.findFirstByName(name);
    }

    @Override
    @Transactional
    public void updateProfile(UserDTO dto) {
        User savedUser = userRepository.findFirstByName(dto.getUsername());
        if (savedUser == null) {
            throw new RuntimeException("User not found by name: " + dto.getUsername());
        }

        boolean changed = false;
        if(dto.getPassword() != null && !dto.getPassword().isEmpty()){
            savedUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            changed = true;
        }
        if(!Objects.equals(dto.getEmail(), savedUser.getEmail())){
            savedUser.setEmail(dto.getEmail());
            changed = true;
        }
        if(changed){
            userRepository.save(savedUser);
        }


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByName(username);

        if (user == null) throw new UsernameNotFoundException("User not found with name:" + username);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
            user.getName(),
            user.getPassword(),
            roles
        );



    }
}
