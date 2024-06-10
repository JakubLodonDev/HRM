package com.jakub.hrm.commands.hruserprofile;

public class CheckPasswordCommandResult {
    public boolean succeeded;
    public String fieldName = "currentPwd";
    public String errorMessage = "";

    public CheckPasswordCommandResult(boolean succeeded, String errorMessage) {
        this.succeeded = succeeded;
        this.errorMessage = errorMessage;
    }

    public CheckPasswordCommandResult(boolean succeeded) {
        this.succeeded = succeeded;
    }
}
