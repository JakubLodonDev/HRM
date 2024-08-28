package com.jakub.hrm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="employment_source")
public class EmploymentSource {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employment_source_id")
    private UUID employeeSourceId;
    @NotBlank(message="Type must not be blank")
    private String sourceType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    private ApplicationForm applicationForm;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public EmploymentSource() {

    }
    public EmploymentSource(String sourceType, ApplicationForm applicationForm, Employee employee) {
        this.sourceType = sourceType;
        this.applicationForm = applicationForm;
        this.employee = employee;
    }

    public UUID getEmployeeSourceId() {
        return employeeSourceId;
    }

    public void setEmployeeSourceId(UUID employeeSourceId) {
        this.employeeSourceId = employeeSourceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public ApplicationForm getApplicationForm() {
        return applicationForm;
    }

    public void setApplicationForm(ApplicationForm applicationForm) {
        this.applicationForm = applicationForm;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
