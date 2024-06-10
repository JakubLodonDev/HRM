package com.jakub.hrm.query.hruser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GetHrUserByIdQuery {

    @NotBlank(message="First Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String firstName;

    @NotBlank(message="Last Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String lastName;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    private String roleName;

    private boolean isPasswordChangeRequired;

    public GetHrUserByIdQuery() {}

    public GetHrUserByIdQuery(String firstName, String lastName, String email, String roleName, boolean isPasswordChangeRequired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roleName = roleName;
        this.isPasswordChangeRequired = isPasswordChangeRequired;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isPasswordChangeRequired() {
        return isPasswordChangeRequired;
    }

    public void setPasswordChangeRequired(boolean passwordChangeRequired) {
        isPasswordChangeRequired = passwordChangeRequired;
    }
}
