package com.br.reserva.service;

import com.br.reserva.dto.user.CreateUserDTO;
import com.br.reserva.dto.user.LoginUserDTO;
import com.br.reserva.dto.user.UpdateUserDTO;
import com.br.reserva.model.User;
import com.br.reserva.repository.UserRepository;
import com.br.reserva.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Jwt jwt;

    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setName(createUserDTO.getName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRole(createUserDTO.getRole());

        return userRepository.save(user);
    }

    public User updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(updateUserDTO.getName());
        user.setEmail(updateUserDTO.getEmail());
        if (updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
        }
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    public String login(LoginUserDTO loginUserDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginUserDTO.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())) {
                return jwt.generateToken(user.getEmail());
            }
        }
        throw new RuntimeException("Invalid email or password");
    }

}
