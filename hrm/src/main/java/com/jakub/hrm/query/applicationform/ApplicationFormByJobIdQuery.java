package com.jakub.hrm.query.applicationform;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class ApplicationFormByJobIdQuery {

    private UUID applicationFormId;
    @NotBlank(message="First name must not be blank")
    private String firstName;
    @NotBlank(message="Last name must not be blank")
    private String lastName;
    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;
    private String employmentStatus;

    public ApplicationFormByJobIdQuery(UUID applicationFormId, String firstName, String lastName, String email,
                                       String employmentStatus) {
        this.applicationFormId = applicationFormId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.employmentStatus = employmentStatus;
    }

    public UUID getApplicationFormId() {
        return applicationFormId;
    }

    public void setApplicationFormId(UUID applicationFormId) {
        this.applicationFormId = applicationFormId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
}
