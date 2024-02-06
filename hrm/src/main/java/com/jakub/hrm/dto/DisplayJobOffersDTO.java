package com.jakub.hrm.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class DisplayJobOffersDTO {


    public UUID jobOfferId;
    @NotBlank(message="First name must not be blank")
    public String name;
    @NotBlank(message="First name must not be blank")
    public String level;
    @NotBlank(message="First name must not be blank")
    public String requirement;
    @NotBlank(message="First name must not be blank")
    public String description;

    public DisplayJobOffersDTO(UUID jobOfferId, String name, String level, String requirement, String description) {
        this.jobOfferId = jobOfferId;
        this.name = name;
        this.level = level;
        this.requirement = requirement;
        this.description = description;
    }
}
