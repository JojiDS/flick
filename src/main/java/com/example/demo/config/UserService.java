package com.example.demo.config;


import com.example.demo.domain.User;
import com.example.demo.domain.UserDTO;
import com.example.demo.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class UserService {

    private final UserRepo repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void create(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        repository.save(user);
    }
}