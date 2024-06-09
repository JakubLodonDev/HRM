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

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    private ApplicationForm applicationForm;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public EmploymentSource(String sourceType, ApplicationForm applicationForm, Employee employee) {
        this.sourceType = sourceType;
        this.applicationForm = applicationForm;
        this.employee = employee;
    }

    public EmploymentSource() {

    }
}
