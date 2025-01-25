package com.br.reserva.controller;

import com.br.reserva.dto.user.CreateUserDTO;
import com.br.reserva.dto.user.LoginUserDTO;
import com.br.reserva.dto.user.UpdateUserDTO;
import com.br.reserva.dto.user.UserResponse;
import com.br.reserva.model.User;
import com.br.reserva.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        User user = userService.createUser(createUserDTO);
        return ResponseEntity.ok(new UserResponse(user.getName(), user.getEmail()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @RequestBody @Valid UpdateUserDTO updateUserDTO) {
        User user = userService.updateUser(id, updateUserDTO);
        return ResponseEntity.ok(new UserResponse(user.getName(), user.getEmail()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(new UserResponse(user.getName(), user.getEmail()));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse> responses = users.stream()
                .map(user -> new UserResponse(user.getName(), user.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginUserDTO loginUserDTO) {
        String token = userService.login(loginUserDTO);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
