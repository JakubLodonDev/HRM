package com.jakub.hrm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name="hr_user")
public class HrUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @NotBlank(message="First Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String firstName;

    @NotBlank(message="Last Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String lastName;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = HrIdentification.class)
    @JoinColumn(name = "identification_id", referencedColumnName = "identification_id", nullable = false)
    private HrIdentification identification;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = HrRole.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private HrRole role;

    public HrUser() {}

    public HrUser(String firstName, String lastName, String email, HrIdentification identification, HrRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.identification = identification;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public HrIdentification getIdentification() {
        return identification;
    }

    public void setIdentification(HrIdentification identification) {
        this.identification = identification;
    }

    public HrRole getRole() {
        return role;
    }

    public void setRole(HrRole roles) {
        this.role = roles;
    }
}
