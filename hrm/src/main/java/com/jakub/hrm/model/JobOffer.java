package com.jakub.hrm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="job_offer")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "job_id")
    private UUID jobOfferId;
    @NotBlank(message="First name must not be blank")
    private String name;
    @NotBlank(message="First name must not be blank")
    private String level;
    @NotBlank(message="First name must not be blank")
    private String requirement;
    @NotBlank(message="First name must not be blank")
    private String description;
    private String status;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    private Set<ApplicationForm> applicationForms;

    public JobOffer() {}

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
