package com.jakub.hrm.model;


import com.jakub.hrm.constans.EmploymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employee_id")
    private UUID employeeId;
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
    private String employmentStatus;
    private String position_name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_address_id", referencedColumnName = "employee_address_id")
    private EmployeeAddress employeeAddress;

    public Employee() {
    }


    public Employee( String firstName, String lastName, String email, String mobilePhone, String employmentStatus, String position_name, EmployeeAddress employeeAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.employmentStatus = employmentStatus;
        this.position_name = position_name;
        this.employeeAddress = employeeAddress;
    }

    public void createNewEmployee(String firstName, String lastName, String email, String mobilePhone, String employmentStatus, String position_name, EmployeeAddress employeeAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.employmentStatus = employmentStatus;
        this.position_name = position_name;
        this.employeeAddress = employeeAddress;
    }

    public void updateEmployee(String firstName, String lastName, String email, String mobilePhone, String position_name, EmployeeAddress employeeAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.position_name = position_name;
        this.employeeAddress = employeeAddress;
    }

    public void setEmploymentStatusOnFire() {
        this.employmentStatus = EmploymentStatus.FIRE;
    }
}
