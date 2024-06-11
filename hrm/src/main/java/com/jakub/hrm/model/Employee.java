package com.jakub.hrm.model;


import com.jakub.hrm.constans.EmploymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cv_attachment_id", referencedColumnName = "cv_attachment_id")
    @PrimaryKeyJoinColumn
    private CvAttachment cvAttachment;

    private UUID cv_attachment_id;

    public Employee() {
    }


    public Employee(String firstName, String lastName, String email, String mobilePhone,
                    String employmentStatus, String position_name,
                    EmployeeAddress employeeAddress, CvAttachment cvAttachment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.employmentStatus = employmentStatus;
        this.position_name = position_name;
        this.employeeAddress = employeeAddress;
        this.cvAttachment = cvAttachment;
    }
    public Employee(UUID employeeId, String firstName, String lastName, String email,
                    String mobilePhone, String employmentStatus, String position_name,
                    EmployeeAddress employeeAddress, CvAttachment cvAttachment) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.employmentStatus = employmentStatus;
        this.position_name = position_name;
        this.employeeAddress = employeeAddress;
        this.cvAttachment = cvAttachment;
    }

    public void createNewEmployee(UUID employeeId, String firstName, String lastName, String email,
                                  String mobilePhone, String employmentStatus, String position_name,
                                  EmployeeAddress employeeAddress, CvAttachment cvAttachment) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.employmentStatus = employmentStatus;
        this.position_name = position_name;
        this.employeeAddress = employeeAddress;
        this.cvAttachment = cvAttachment;
    }

    public void updateEmployee(String firstName, String lastName, String email,
                               String mobilePhone, String position_name,
                               EmployeeAddress employeeAddress) {
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

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
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

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public EmployeeAddress getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public CvAttachment getCvAttachment() {
        return cvAttachment;
    }

    public void setCvAttachment(CvAttachment cvAttachment) {
        this.cvAttachment = cvAttachment;
    }

    public UUID getCv_attachment_id() {
        return cv_attachment_id;
    }

    public void setCv_attachment_id(UUID cv_attachment_id) {
        this.cv_attachment_id = cv_attachment_id;
    }
}
