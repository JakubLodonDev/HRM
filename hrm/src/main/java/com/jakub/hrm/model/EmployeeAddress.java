package com.jakub.hrm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="employee_address")
public class EmployeeAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employee_address_id")
    private UUID employeeAddressId;
    @NotBlank(message="Address must not be blank")
    @Size(min=5, message="Address must be at least 5 characters long")
    private String streetAddress;
    @NotBlank(message="City must not be blank")
    @Size(min=5, message="City must be at least 5 characters long")
    private String city;
    @NotBlank(message="State must not be blank")
    @Size(min=5, message="State must be at least 5 characters long")
    private String country;
    @NotBlank(message="Zip Code must not be blank")
    @Pattern(regexp="(^$|[0-9]{5})",message = "Zip Code must be 5 digits")
    private String zipCode;

    public EmployeeAddress() {
    }

    public EmployeeAddress(String streetAddress, String city, String country, String zipCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }
}
