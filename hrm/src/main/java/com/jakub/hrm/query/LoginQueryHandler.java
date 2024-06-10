package com.jakub.hrm.query;

public class LoginQueryHandler {
    public static String Handle(String error, String logout) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        return errorMessage;
    }
}
