package com.br.reserva.validator;

import com.br.reserva.model.Role;
import com.br.reserva.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorUser {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailValidationWhenNameIsBlank() {
        User user = new User();
        user.setEmail("example@example.com");
        user.setPassword("Secret");
        user.setName("");
        user.setRole(Role.USER);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        assertEquals("Name cannot be null or blank", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenNameIsNull() {
        User user = new User();
        user.setEmail("example@example.com");
        user.setPassword("Secret");
        user.setName(null);
        user.setRole(Role.USER);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(1, violations.size());
        assertEquals("Name cannot be null or blank", violations.iterator().next().getMessage());
    }

    @Test
    void shouldPassValidationWhenNameIsValid() {
        User user = new User();
        user.setEmail("example@example.com");
        user.setPassword("Secret");
        user.setName("Valid User");
        user.setRole(Role.USER);

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(0, violations.size());
    }
}
