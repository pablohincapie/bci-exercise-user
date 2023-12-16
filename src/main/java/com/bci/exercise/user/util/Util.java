package com.bci.exercise.user.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final String EXPRESSION_EMAIL = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$";
    private static final String EXPRESSION_PASSWORD = "^(?=.*[A-Z])(?=.*\\d.*\\d)(?!.*\\d.*\\d.*\\d)(?!.*[^a-zA-Z\\d]).{8,12}$";

    public static boolean isValidEmail(String email) {
        String regex = EXPRESSION_EMAIL;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        String regex = EXPRESSION_PASSWORD;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
