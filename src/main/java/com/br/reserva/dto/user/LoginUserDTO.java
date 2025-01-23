package com.br.reserva.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginUserDTO {
    @Email(message = "Email invalid")
    @NotBlank(message = "The user's email must not be blank")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    private String password;
}
