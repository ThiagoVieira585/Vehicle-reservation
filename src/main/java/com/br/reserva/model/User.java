package com.br.reserva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Email
    @NotBlank(message = "The user's email must not be blank")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "The user's role is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @NotBlank(message = "Name cannot be null or blank")
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    private void setCreationDate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void setUpdateDate() {
        this.updatedAt = LocalDateTime.now();
    }
}
