package com.jakub.hrm.commands.employee;

import com.jakub.hrm.model.ApplicationForm;

public class NewEmploymentSourceCommand {

    private String sourceType;

    public NewEmploymentSourceCommand() {
    }

    public String getSourceType() {
        return sourceType;
    }
}
