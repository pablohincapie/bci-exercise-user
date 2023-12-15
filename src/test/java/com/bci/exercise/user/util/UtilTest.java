package com.bci.exercise.user.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilTest {
    @Test
    public void testIsValidEmail() {

        String validEmail = "pablohincapie@hotmail.com";
        String invalidEmail = "pablohincapiehotmailcom";

        assertTrue(Util.isValidEmail(validEmail));
        assertFalse(Util.isValidEmail(invalidEmail));
    }

    @Test
    public void testIsValidPassword() {

        String validPassword = "Abcd12efg";
        String invalidPassword1 = "short";
        String invalidPassword2 = "toolongpassword123";
        String invalidPassword3 = "abcd12efg";
        String invalidPassword4 = "abcdEfghijk";

        assertTrue(Util.isValidPassword(validPassword));
        assertFalse(Util.isValidPassword(invalidPassword1));
        assertFalse(Util.isValidPassword(invalidPassword2));
        assertFalse(Util.isValidPassword(invalidPassword3));
        assertFalse(Util.isValidPassword(invalidPassword4));
    }
}
