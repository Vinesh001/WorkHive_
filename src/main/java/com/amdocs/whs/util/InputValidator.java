package com.amdocs.whs.util;

public class InputValidator {

    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean isValidNumber(String input) {
        if (isEmpty(input)) return false;
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidInteger(String input) {
        if (isEmpty(input)) return false;
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}
