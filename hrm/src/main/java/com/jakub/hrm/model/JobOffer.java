package com.jakub.hrm.model;

import com.jakub.hrm.constans.JobStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="job_offer")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "job_id")
    private UUID jobOfferId;
    @NotBlank(message="Name must not be blank")
    private String name;
    @NotBlank(message="Level must not be blank")
    private String level;
    @NotBlank(message="Requirement must not be blank")
    private String requirement;
    @NotBlank(message="Description must not be blank")
    private String description;
    @NotBlank(message="Status must not be blank")
    private String status;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER,
            targetEntity = ApplicationForm.class)
    private Set<ApplicationForm> applicationForms;

    public JobOffer() {}

    public JobOffer(UUID jobOfferId, String name, String level, String requirement, String description, String status) {
        this.jobOfferId = jobOfferId;
        this.name = name;
        this.level = level;
        this.requirement = requirement;
        this.description = description;
        this.status = status;
    }

    public void updateData(String name, String level, String requirement, String description) {
        this.name = name;
        this.level = level;
        this.requirement = requirement;
        this.description = description;
    }

    public void closeJobOffer() {
        this.status = JobStatus.CLOSE;
    }

    public void updateStatus(String close) {
        this.status = close;
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

    public Set<ApplicationForm> getApplicationForms() {
        return applicationForms;
    }

    public void setApplicationForms(Set<ApplicationForm> applicationForms) {
        this.applicationForms = applicationForms;
    }
}
