package com.bloomtech.socialfeed.validators;

import com.bloomtech.socialfeed.exceptions.UserValidationException;
import com.bloomtech.socialfeed.models.Role;
import com.bloomtech.socialfeed.models.User;

public class UserInfoValidator implements Validator {

    private boolean isValidUsername(String username) {
        //Username must begin Uppercase
        if (!Character.isUpperCase(username.charAt(0))) {
            return false;
        }
        //Username is min 4 letters long
        if (username.length() < 4) {
            return false;
        }
        //contains at letters, numbers, and underscores
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        // Check for uppercase letter
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(".*[A-Z].*");
        java.util.regex.Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return false;
        }

        // Check for lowercase letter
        pattern = java.util.regex.Pattern.compile(".*[a-z].*");
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return false;
        }

        // Check for at least one digit
        pattern = java.util.regex.Pattern.compile(".*\\d.*");
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return false;
        }

        // Check for valid symbols
        pattern = java.util.regex.Pattern.compile("^[a-zA-Z0-9!@#$%^&*]+$");
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return false;
        }

        return true;
    }

    @Override
    public void validate(Object userData) {

        User user = (User) userData;

        if (!isValidUsername(user.getUsername())) {
            throw new UserValidationException("Invalid Username: Username must be at least 4 characters long, " +
                    "must begin with an uppercase letter, and may not contain special characters or spaces!");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new UserValidationException("Invalid Password: Password must be at least 8 characters long, " +
                    "contain at least one uppercase letter, one lowercase letter, and one special character!");
        }
        if (user.getRole() == null) { user.setRole(Role.USER); }
    }
}
