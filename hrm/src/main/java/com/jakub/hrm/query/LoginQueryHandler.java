package com.jakub.hrm.query;

public class LoginQueryHandler {
    public static String Handle(String error, String logout) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        return errorMessge;
    }
}
