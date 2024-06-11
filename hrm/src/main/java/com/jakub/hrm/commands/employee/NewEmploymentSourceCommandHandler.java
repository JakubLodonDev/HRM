package com.jakub.hrm.commands.employee;

import com.jakub.hrm.model.ApplicationForm;
import com.jakub.hrm.model.Employee;
import com.jakub.hrm.model.EmploymentSource;
import com.jakub.hrm.repo.EmploymentSourceRepo;
import com.jakub.hrm.repo.applicationform.ApplicationFormRepo;
import com.jakub.hrm.repo.employee.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NewEmploymentSourceCommandHandler {

    EmployeeRepo employeeRepo;
    ApplicationFormRepo applicationFormRepo;
    EmploymentSourceRepo employmentSourceRepo;

    @Autowired
    public NewEmploymentSourceCommandHandler(EmployeeRepo employeeRepo, ApplicationFormRepo applicationFormRepo,
                                             EmploymentSourceRepo employmentSourceRepo) {
        this.employeeRepo = employeeRepo;
        this.applicationFormRepo = applicationFormRepo;
        this.employmentSourceRepo = employmentSourceRepo;
    }

    public void Handle(String sourceType, UUID employeeId, UUID applicationFormId) {

        Optional<Employee> dbEmployee = employeeRepo.findById(employeeId);
        Optional<ApplicationForm> applicationForm = applicationFormRepo.findById(applicationFormId);
        if(dbEmployee.isPresent()) {
            Employee employee = dbEmployee.get();

            EmploymentSource employmentSource = new EmploymentSource(sourceType,
                    applicationForm.get(), employee);
            employmentSourceRepo.save(employmentSource);
        }
    }

    public void Handle(String sourceType, UUID employeeId) {

        Optional<Employee> dbEmployee = employeeRepo.findById(employeeId);
        if(dbEmployee.isPresent()) {
            Employee employee = dbEmployee.get();

            EmploymentSource employmentSource = new EmploymentSource(sourceType,
                    null, employee);
            employmentSourceRepo.save(employmentSource);
        }
    }
}
