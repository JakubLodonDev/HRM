package com.jakub.hrm.commands.hruser;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NewHrUserCommand {

    @NotBlank(message="First Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String firstName;

    @NotBlank(message="Last Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String lastName;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @Valid
    private NewHrIdentificationCommand identification;

    private NewHrRoleCommand role;

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

    public NewHrIdentificationCommand getIdentification() {
        return identification;
    }

    public void setIdentification(NewHrIdentificationCommand identification) {
        this.identification = identification;
    }

    public NewHrRoleCommand getRole() {
        return role;
    }

    public void setRole(NewHrRoleCommand role) {
        this.role = role;
    }
}
