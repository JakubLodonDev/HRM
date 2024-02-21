package com.jakub.hrm.query.applicationform;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public class ApplicationFormQuery {

    private UUID applicationFormId;
    @NotBlank(message="First name must not be blank")
    private String firstName;
    @NotBlank(message="Last name must not be blank")
    private String lastName;
    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;
    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobilePhone;
    private String aboutYourself;

    private String employmentStatus;

    public ApplicationFormQuery(UUID applicationFormId, String firstName, String lastName, String email, String mobilePhone, String aboutYourself, String employmentStatus) {
        this.applicationFormId = applicationFormId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.aboutYourself = aboutYourself;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
}
