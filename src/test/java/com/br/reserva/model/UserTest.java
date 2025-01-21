package com.br.reserva.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void shouldCreateUser() {
        // Configuração do objeto User
        user.setEmail("test@test.com");
        user.setName("Test User");
        user.setRole(Role.USER);
        user.setPassword("secret"); // Criptografia no setter
        user.setCreatedAt(LocalDateTime.now());

        assertNotNull(user.getEmail());
        assertNotNull(user.getPassword());
        assertNotNull(user.getRole());
        assertNotNull(user.getName());
        assertNotNull(user.getCreatedAt());
        assertNull(user.getUpdatedAt());
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        user.setEmail("test@test.com");
        user.setPassword("SecurePassword");
        user.setRole(Role.USER);
        user.setName("Test User");
        user.setCreatedAt(LocalDateTime.now());

        user.setName("Test Updated");
        user.setUpdatedAt(LocalDateTime.now());

        assertEquals("Test Updated", user.getName());
        assertNotNull(user.getUpdatedAt());
    }

}
