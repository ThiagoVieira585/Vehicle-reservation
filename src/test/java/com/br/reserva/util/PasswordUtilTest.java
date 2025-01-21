package com.br.reserva.util;

import com.br.reserva.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordUtilTest {
    @Test
    void testPasswordEncryption() {
        User user = new User();
        String rawPassword = "Secret";

        user.setPassword(rawPassword);

        String encryptedPassword = user.getPassword();
        assertNotEquals(rawPassword, encryptedPassword, "The password should not be stored in plain text");

        assertTrue(BCrypt.checkpw(rawPassword, encryptedPassword),
                "The encrypted password should be correctly validated with the raw password");
    }
}
