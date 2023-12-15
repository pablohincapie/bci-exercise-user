package com.bci.exercise.user.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String candidate, String hashedPassword) {
        return BCrypt.checkpw(candidate, hashedPassword);
    }
}
