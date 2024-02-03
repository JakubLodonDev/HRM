package com.jakub.hrm.dto;

import com.jakub.hrm.model.Address;
import com.jakub.hrm.model.JobOffer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public class SubmittedApplicationDTO {

    @NotBlank(message="First name must not be blank")
    public String firstName;
    @NotBlank(message="Last name must not be blank")
    public String lastName;
    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    public String email;
    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    public String mobilePhone;
    public String aboutYourself;
    public String employmentStatus;
    @Valid
    public AddressDTO address;

    public SubmittedApplicationDTO() {
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
