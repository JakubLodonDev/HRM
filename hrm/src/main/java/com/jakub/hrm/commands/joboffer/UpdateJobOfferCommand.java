package com.jakub.hrm.commands.joboffer;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class UpdateJobOfferCommand {

    private UUID jobOfferId;
    @NotBlank(message="First name must not be blank")
    private String name;
    @NotBlank(message="Level must not be blank")
    private String level;
    @NotBlank(message="Requirement must not be blank")
    private String requirement;
    @NotBlank(message="Description must not be blank")
    private String description;
    @NotBlank(message="Status must not be blank")
    private String status;

    public UpdateJobOfferCommand(UUID jobOfferId, String name, String level, String requirement, String description, String status) {
        this.jobOfferId = jobOfferId;
        this.name = name;
        this.level = level;
        this.requirement = requirement;
        this.description = description;
        this.status = status;
    }

    public UUID getJobOfferId() {
        return jobOfferId;
    }

    public void setJobOfferId(UUID jobOfferId) {
        this.jobOfferId = jobOfferId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
