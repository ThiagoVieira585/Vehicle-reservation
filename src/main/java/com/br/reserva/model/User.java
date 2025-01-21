package com.br.reserva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    private String password;

    @NotBlank(message = "The user's email must not be blank")
    @Column(nullable = false, unique = true)
    private String email;

    public void setPassword(String password) {
        if (password != null && !password.trim().isEmpty()) {
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        }
    }

    @NotNull(message = "The user's role is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @NotBlank(message = "Name cannot be null or blank")
    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
