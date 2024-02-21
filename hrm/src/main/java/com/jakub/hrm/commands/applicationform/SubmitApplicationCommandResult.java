package com.jakub.hrm.commands.applicationform;

public class SubmitApplicationCommandResult {
    public boolean succeeded;
    public String fieldName = "email";
    public String errorCode = "email.applicationForm";
    public String errorMessage = "Osoba o tym adresie e-mail już aplikowała na to stanowisko.";

    public SubmitApplicationCommandResult(boolean succeeded, String errorMessage) {
        this.succeeded = succeeded;
        this.errorMessage = errorMessage;
    }

    public SubmitApplicationCommandResult(boolean succeeded) {
        this.succeeded = succeeded;
    }
}
