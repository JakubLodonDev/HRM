package com.jakub.hrm.commands.employee;

import com.jakub.hrm.model.ApplicationForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

public class NewEmploymentSourceCommand {

    private String sourceType;

    public NewEmploymentSourceCommand() {
    }

    public String getSourceType() {
        return sourceType;
    }
}
