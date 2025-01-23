package com.br.reserva.dto.user;

import com.br.reserva.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    private String password;

    @NotBlank(message = "The user's email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "The user's role is required")
    private Role role;

    @NotBlank(message = "Name cannot be null or blank")
    private String name;
}
