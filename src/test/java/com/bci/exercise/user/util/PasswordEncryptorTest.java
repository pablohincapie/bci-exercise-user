package com.bci.exercise.user.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncryptorTest {

    @Test
    public void testEncryptPassword() {

        String password = "A12dfgra";
        String hashedPassword = PasswordEncryptor.encryptPassword(password);
        assertTrue(PasswordEncryptor.checkPassword(password, hashedPassword));
    }

    @Test
    public void testCheckPassword() {

        String password = "A12dfgra";
        String hashedPassword = PasswordEncryptor.encryptPassword(password);
        assertTrue(PasswordEncryptor.checkPassword(password, hashedPassword));
    }
}
